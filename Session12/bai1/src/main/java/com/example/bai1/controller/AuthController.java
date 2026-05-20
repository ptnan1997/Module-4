package com.example.bai1.controller;

import com.example.bai1.dto.request.LoginRequest;
import com.example.bai1.dto.request.RegisterRequest;
import com.example.bai1.dto.response.AuthResponse;
import com.example.bai1.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse rs = authService.register(request);
        return new  ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
