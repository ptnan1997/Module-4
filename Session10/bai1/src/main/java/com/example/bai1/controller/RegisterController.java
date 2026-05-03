package com.example.bai1.controller;

import com.example.bai1.dto.RegisterRequest;
import com.example.bai1.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class RegisterController {
    private final AuthService authService;
    public  RegisterController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        authService.register(registerRequest);
        return new  ResponseEntity<>(registerRequest, HttpStatus.OK);
    }

}
