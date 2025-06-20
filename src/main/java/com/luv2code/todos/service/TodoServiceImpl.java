package com.luv2code.todos.service;
import com.luv2code.todos.entity.Todo;
import com.luv2code.todos.entity.User;
import com.luv2code.todos.repository.TodoRepository;
import com.luv2code.todos.request.TodoRequest;
import com.luv2code.todos.response.TodoResponse;
import com.luv2code.todos.util.FindAuthenticatedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public TodoServiceImpl(TodoRepository todoRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.todoRepository = todoRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    @Transactional
    public TodoResponse createTodo(TodoRequest todoRequest) {
        User crruentUser = findAuthenticatedUser.getAuthenticatedUser();
        Todo todo = new Todo(
                todoRequest.getTitle(),
                todoRequest.getDescription(),
                todoRequest.getPriority(),
                false,
                crruentUser
        );

        Todo saveTodo = todoRepository.save(todo);

        return new TodoResponse(saveTodo.getId(),
                saveTodo.getTitle(),
                saveTodo.getDescription(),
                saveTodo.isComplete(),
                saveTodo.getPriority());
    }




}
