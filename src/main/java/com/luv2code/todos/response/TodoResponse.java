package com.luv2code.todos.response;

public class TodoResponse {

    private long id ;

    private String title ;

    private String description ;

    private int priority ;

    private boolean complete ;

    public TodoResponse(long id, String title, String description, boolean complete, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.complete = complete;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
