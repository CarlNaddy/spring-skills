package com.example.referenceapp.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(UserForm userForm) {
        User user = new User(null, userForm.getName().trim(), userForm.getEmail().trim().toLowerCase());
        return userRepository.save(user);
    }

    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }
}

