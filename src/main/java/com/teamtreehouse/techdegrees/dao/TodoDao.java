package com.teamtreehouse.techdegrees.dao;

import com.teamtreehouse.techdegrees.model.Todo;

import java.util.List;

public interface TodoDao {
    List<Todo> findAll();

    void add(Todo todo);

    void update(Todo todo);

    void delete(Todo todo);

    Todo findById(int id);
}
