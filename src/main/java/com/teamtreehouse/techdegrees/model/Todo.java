package com.teamtreehouse.techdegrees.model;

import java.util.Objects;

public class Todo {
    private int id;
    private String name;
    private boolean isCompleted;

    public Todo(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id &&
                isCompleted == todo.isCompleted &&
                Objects.equals(name, todo.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, isCompleted);
    }
}
