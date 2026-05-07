package com.example.bai1.controller;

import com.example.bai1.model.dto.request.UserLoginDTO;
import com.example.bai1.model.dto.request.UserRegisterDTO;
import com.example.bai1.model.dto.request.VerifyOtpRequest;
import com.example.bai1.model.dto.response.UserRegisterResponse;
import com.example.bai1.security.UserDetailServiceCustom;
import com.example.bai1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailServiceCustom userDetailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return new ResponseEntity<>(userService.register(userRegisterDTO), HttpStatus.OK);
    }

    @PostMapping("/active-user")
    public ResponseEntity<String> activeUser(@RequestBody VerifyOtpRequest request) {
        String message = userService.verifyAccount(request);
        return ResponseEntity.ok(message);
    }
}
