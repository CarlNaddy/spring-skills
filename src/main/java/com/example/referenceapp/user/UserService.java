package com.example.referenceapp.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User create(UserForm userForm) {
        User user = new User(
                null,
                userForm.getName().trim(),
                userForm.getEmail().trim().toLowerCase(),
                normalizeOptional(userForm.getAddress()),
                normalizeOptional(userForm.getPhoneNumber())
        );
        return userRepository.save(user);
    }

    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    private String normalizeOptional(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value.trim();
        return normalized.isEmpty() ? null : normalized;
    }
}

