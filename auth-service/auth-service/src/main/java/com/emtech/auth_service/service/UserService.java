package com.emtech.auth_service.service;

import com.emtech.auth_service.model.User;
import com.emtech.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String fullName, String email, String password, String role) {
        if (userRepository.existsByFullName(fullName)) {
            throw new RuntimeException("User already exists");
        }

        // ✅ Convert role String to Enum
        User.Role userRole;
        try {
            userRole = User.Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }

        User user = User.builder()
                .fullName(fullName)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(userRole)  // ✅ Dynamically assign role
                .build();

        return userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        // Find user by email
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return false; // User not found
        }

        User user = userOptional.get();

        // Check if the password is correct
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return false; // Incorrect password
        }

        return true; // Successful authentication
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
            return userRepository.findAll();
    }

    public Optional<User> updateUserByEmail(String email, User updatedUser) {
        return userRepository.findByEmail(email).map(existingUser -> {
            existingUser.setFullName(updatedUser.getFullName());

            // Ensure password is hashed before updating
            if (!updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        });
    }

    public boolean deleteUserByEmail(String email) {
        return userRepository.findByEmail(email).map(user -> {
            userRepository.delete(user);
            log.info("User deleted successfully: {}", email);
            return true;
        }).orElse(false);
    }
}
