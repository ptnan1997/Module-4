package com.example.bai1.service;

import com.example.bai1.dto.RegisterRequest;
import com.example.bai1.entity.User;
import com.example.bai1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(RegisterRequest dto) {

        // 1. Check trùng username
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // 2. Tạo user
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());

        // 🔥 QUAN TRỌNG
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRole("USER");
        user.setEnabled(true);

        // 3. Save DB
        userRepository.save(user);
    }
}
