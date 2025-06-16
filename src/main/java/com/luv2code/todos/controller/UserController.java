package com.luv2code.todos.controller;

import com.luv2code.todos.entity.User;
import com.luv2code.todos.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public User getUserInfo(){
        return  userService.getUserInfo();
    }
}



