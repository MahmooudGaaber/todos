package com.luv2code.todos.controller;

import com.luv2code.todos.entity.User;
import com.luv2code.todos.request.PasswordUpdateRequest;
import com.luv2code.todos.response.UserResponse;
import com.luv2code.todos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    @Operation(summary = "Get Current User Info")
    public UserResponse getUserInfo(){
        return  userService.getUserInfo();
    }

    @DeleteMapping
    public void deleteUser(){
        userService.deleteUser();
    }

    @PutMapping("/password")
    @Operation(summary = "Update Current User Password")
    public void passwordUpdate(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) throws Exception {
        userService.updatePassword(passwordUpdateRequest);
    }
}



