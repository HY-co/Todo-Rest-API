package com.teamtreehouse.techdegrees.dao;

import com.teamtreehouse.techdegrees.exception.DaoException;
import com.teamtreehouse.techdegrees.model.Todo;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTodoDao implements TodoDao {

    private final Sql2o sql2o;

    public Sql2oTodoDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Todo> findAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM todos")
                    .addColumnMapping("is_completed", "isCompleted")
                    .executeAndFetch(Todo.class);
        }
    }

    @Override
    public void add(Todo todo) {
        String sql = "INSERT INTO todos(name, is_completed) VALUES (:name, :isCompleted)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addColumnMapping("is_completed", "isCompleted")
                    .bind(todo)
                    .executeUpdate()
                    .getKey();
            todo.setId(id);
        }catch (Sql2oException ex) {
            throw new DaoException(ex, "Problem adding todo");
        }
    }

    @Override
    public void update(Todo todo) {
        String sql = "UPDATE todos SET name = :name, is_completed = :isCompleted WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addColumnMapping("is_completed", "isCompleted")
                    .bind(todo)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            throw new DaoException(ex, "Problem updating todo");
        }
    }

    @Override
    public void delete(Todo todo) {
        String sql = "DELETE FROM todos WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", todo.getId())
                    .executeUpdate();
        }catch (Sql2oException ex) {
            throw new DaoException(ex, "Problem deleting todo");
        }
    }

    @Override
    public Todo findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM todos WHERE id = :id")
                    .addColumnMapping("is_completed", "isCompleted")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Todo.class);
        }
    }

}
