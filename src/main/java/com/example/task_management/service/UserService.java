package com.example.task_management.service;

import com.example.task_management.model.Role;
import com.example.task_management.model.User;
import com.example.task_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        System.out.println("Registering user: " + user.getUsername());  // Debugging
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        System.out.println("User saved with ID: " + savedUser.getId());  // Debugging
        return savedUser;
    }

    public boolean passwordMatches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }



    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
