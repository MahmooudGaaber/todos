package com.luv2code.todos.service;

import com.luv2code.todos.request.RegisterRequest;

public interface AuthenticationService {

    void register(RegisterRequest input) throws Exception ;
}
