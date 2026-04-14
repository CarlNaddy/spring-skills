package com.example.referenceapp.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    boolean deleteById(Long id);

    List<Todo> findTodosByUserId(Long userId);

    Optional<Todo> addTodo(Long userId, String text);

    boolean deleteTodoById(Long userId, Long todoId);

    boolean markTodoDone(Long userId, Long todoId);
}

