package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserRepository;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("User already exists");
        }
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
