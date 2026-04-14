package com.example.referenceapp.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private final Map<Long, List<Todo>> todosByUserId = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);
    private final AtomicLong todoSequence = new AtomicLong(0);

    @Override
    public List<User> findAll() {
        return users.stream()
                .sorted(Comparator.comparing(User::id))
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.id().equals(id))
                .findFirst();
    }

    @Override
    public User save(User user) {
        User savedUser = new User(
                sequence.incrementAndGet(),
                user.name(),
                user.email(),
                user.address(),
                user.phoneNumber()
        );
        users.add(savedUser);
        todosByUserId.put(savedUser.id(), new ArrayList<>());
        return savedUser;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean removed = users.removeIf(user -> user.id().equals(id));
        if (removed) {
            todosByUserId.remove(id);
        }
        return removed;
    }

    @Override
    public List<Todo> findTodosByUserId(Long userId) {
        return todosByUserId.getOrDefault(userId, List.of()).stream()
                .sorted(Comparator.comparing(Todo::id))
                .toList();
    }

    @Override
    public Optional<Todo> addTodo(Long userId, String text) {
        if (findById(userId).isEmpty()) {
            return Optional.empty();
        }
        List<Todo> todos = todosByUserId.computeIfAbsent(userId, ignored -> new ArrayList<>());
        Todo todo = new Todo(todoSequence.incrementAndGet(), userId, text, false);
        todos.add(todo);
        return Optional.of(todo);
    }

    @Override
    public boolean deleteTodoById(Long userId, Long todoId) {
        List<Todo> todos = todosByUserId.get(userId);
        if (todos == null) {
            return false;
        }
        return todos.removeIf(todo -> todo.id().equals(todoId));
    }

    @Override
    public boolean markTodoDone(Long userId, Long todoId) {
        List<Todo> todos = todosByUserId.get(userId);
        if (todos == null) {
            return false;
        }

        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            if (todo.id().equals(todoId)) {
                if (todo.done()) {
                    return false;
                }
                todos.set(i, new Todo(todo.id(), todo.userId(), todo.text(), true));
                return true;
            }
        }
        return false;
    }
}

