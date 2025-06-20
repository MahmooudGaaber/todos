package com.luv2code.todos.service;

import com.luv2code.todos.request.TodoRequest;
import com.luv2code.todos.response.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse createTodo(TodoRequest todoRequest);

    List<TodoResponse> getAllTodos();

    TodoResponse toggleTodoCompletion(long id);

    void deleteTodo(long id);
}
