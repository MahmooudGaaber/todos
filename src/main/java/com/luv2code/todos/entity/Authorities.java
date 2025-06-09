package com.luv2code.todos.entity;

import jakarta.persistence.Embeddable;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
public class Authorities implements GrantedAuthority {

    private String authority;

    public Authorities() {
    }

    public Authorities(String authority) {
        this.authority = authority;
    }



    @Override
    public String getAuthority() {
        return "";
    }


}
