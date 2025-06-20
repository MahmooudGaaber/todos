package com.luv2code.todos.service;
import com.luv2code.todos.entity.Todo;
import com.luv2code.todos.entity.User;
import com.luv2code.todos.repository.TodoRepository;
import com.luv2code.todos.request.TodoRequest;
import com.luv2code.todos.response.TodoResponse;
import com.luv2code.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

        return covertToTodoResponse(saveTodo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        User crruentUser = findAuthenticatedUser.getAuthenticatedUser();
        return todoRepository.findByOwner(crruentUser).stream().map(this::covertToTodoResponse).toList();
    }

    @Override
    @Transactional
    public TodoResponse toggleTodoCompletion(long id) {
        User crruentUser = findAuthenticatedUser.getAuthenticatedUser();
        Optional<Todo> todo = todoRepository.findByIdAndOwner(id,crruentUser);
        if(todo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Todo Not Found");
        }
        todo.get().setComplete(!todo.get().isComplete());
        Todo updatedTodo = todoRepository.save(todo.get());
        return covertToTodoResponse(updatedTodo);
    }

    private TodoResponse covertToTodoResponse(Todo todo){
        return new TodoResponse(todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isComplete(),
                todo.getPriority());
    }


}
