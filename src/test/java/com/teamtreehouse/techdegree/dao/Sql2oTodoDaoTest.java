package com.teamtreehouse.techdegree.dao;

import com.teamtreehouse.techdegrees.dao.Sql2oTodoDao;
import com.teamtreehouse.techdegrees.model.Todo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oTodoDaoTest {
    private Sql2oTodoDao todoDao;
    private Connection conn;
    private Todo todo;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:test;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        todoDao = new Sql2oTodoDao(sql2o);
        todo = new Todo("test", true);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingTodoSetsId() throws Exception {
        int originalTodoId = todo.getId();

        todoDao.add(todo);
        assertNotEquals(originalTodoId, todo.getId());
    }

    @Test
    public void addedTodsAreReturnedFromFindAll() throws Exception {
        todoDao.add(todo);
        Todo todo2 = new Todo("test 2", false);
        todoDao.add(todo2);

        assertEquals(2, todoDao.findAll().size());
    }

    @Test
    public void noTodosReturnEmptyList() throws Exception {
        assertEquals(0, todoDao.findAll().size());
    }

    @Test
    public void todoShouldBeUpdatedAfterCallUpdateMethod() throws Exception {
        todoDao.add(todo);
        todo.setName("test1");
        todoDao.update(todo);

        assertEquals("test1", todo.getName());
    }

    @Test
    public void todoShouldBeDeletedAfterCallDeleteMethod() throws Exception {
        todoDao.add(todo);
        assertEquals(1, todoDao.findAll().size()); // make sure todo is added successfully

        todoDao.delete(todo);
        assertEquals(0, todoDao.findAll().size());
    }
}