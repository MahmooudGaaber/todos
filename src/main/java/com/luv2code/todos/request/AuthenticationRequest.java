package com.luv2code.todos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 5 ,max = 30 , message = "Password must be 5 character long")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}




