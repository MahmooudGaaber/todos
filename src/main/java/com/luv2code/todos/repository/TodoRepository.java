package com.luv2code.todos.repository;

import com.luv2code.todos.entity.Todo;
import com.luv2code.todos.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Long> {
    List<Todo> findByOwner(User owner);
}
