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

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "owner_id",nullable = false)
     private User owner;


    public Todo() {}

    public Todo(String title, String description, int priority, boolean complete, User owner) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.complete = complete;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
