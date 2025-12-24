package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ THIS FIXES YOUR ERROR
public class UserService {

    private final UserRepository repository;

    // Constructor Injection
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Create user
    public User createUser(User user) {

        if (user == null) {
            throw new ValidationException("User cannot be null");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }

        return repository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // Get user by id
    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
