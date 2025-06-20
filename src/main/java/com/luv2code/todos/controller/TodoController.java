package com.luv2code.todos.controller;


import com.luv2code.todos.request.TodoRequest;
import com.luv2code.todos.response.TodoResponse;
import com.luv2code.todos.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo Rest API endpoints")
public class TodoController {
    private final TodoService todoService ;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Create Todo For Users" , description = "Only Signed Users")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TodoResponse createTodo(@Valid @RequestBody TodoRequest todoRequest){
        return todoService.createTodo(todoRequest);
    }



    @Operation(summary = "Get ALL Todo For Users" , description = "Only Signed Users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<TodoResponse> getAllTodos(){
        return todoService.getAllTodos();
    }


    @Operation(summary = "Change Todo Completion" , description = "Only Signed Users")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public TodoResponse toggleTodoCompletion(@PathVariable @Min(1) long id ){
        return  todoService.toggleTodoCompletion(id);
    }

    @Operation(summary = "Get ALL Todo For Users" , description = "Only Signed Users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable @Min(1) long id ){
          todoService.deleteTodo(id);
    }

}


