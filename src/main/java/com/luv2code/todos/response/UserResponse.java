package com.luv2code.todos.response;

import com.luv2code.todos.entity.Authorities;

import java.util.List;

public class UserResponse {

    private long id;

    private String fullName;

    private String email;

    private List<Authorities> authorities ;

    public UserResponse(long id, List<Authorities> authorities, String email, String fullName) {
        this.id = id;
        this.authorities = authorities;
        this.email = email;
        this.fullName = fullName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }
}
