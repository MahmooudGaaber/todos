package com.luv2code.todos.controller;

import com.luv2code.todos.response.UserResponse;
import com.luv2code.todos.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin API endpoints")
public class AdminController {
    private final AdminService adminService ;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
     public List<UserResponse> getAllUsers(){
        return adminService.getAllUsers();
     }

    @PutMapping("/{userId}/role")
    @ResponseStatus(HttpStatus.OK)
     public UserResponse promoteToAdmin(@PathVariable @Min(1) long userId){
        return adminService.promoteToAdmin(userId);
     }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deleteUser (@PathVariable @Min(1) long userId){
        adminService.deleteNonAdminUser(userId);
     }


}
