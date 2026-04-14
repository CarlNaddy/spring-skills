package com.example.referenceapp.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(0);

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
        User savedUser = new User(sequence.incrementAndGet(), user.name(), user.email());
        users.add(savedUser);
        return savedUser;
    }

    @Override
    public boolean deleteById(Long id) {
        return users.removeIf(user -> user.id().equals(id));
    }
}

