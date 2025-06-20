package com.luv2code.todos.service;

import com.luv2code.todos.request.TodoRequest;
import com.luv2code.todos.response.TodoResponse;

public interface TodoService {

    TodoResponse createTodo(TodoRequest todoRequest);
}
