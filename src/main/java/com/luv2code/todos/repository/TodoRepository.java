package com.luv2code.todos.repository;

import com.luv2code.todos.entity.Todo;
import com.luv2code.todos.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Long> {
    List<Todo> findByOwner(User owner);
    Optional<Todo> findByIdAndOwner(long id , User owner);
}
