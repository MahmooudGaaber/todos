package com.luv2code.todos.entity;


import jakarta.persistence.*;

import java.security.PrivateKey;

@Entity
@Table(name = "todos")
public class Todo {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Id
    private long id ;

    @Column(nullable = false)
    private String title ;

    @Column(nullable = false)
    private String description ;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private  boolean complete ;

    // private User owner;


    public Todo() {}

    public Todo(String title, String description, int priority, boolean complete) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.complete = complete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
