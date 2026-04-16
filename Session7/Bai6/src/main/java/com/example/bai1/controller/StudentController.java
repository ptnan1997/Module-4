package com.example.bai1.controller;

import com.example.bai1.dto.ParentDTO;
import com.example.bai1.dto.StudentDTO;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @PostMapping
    public ResponseEntity<?> createStudent (
            @Valid @RequestBody StudentDTO studentDTO, ParentDTO parent
            , BindingResult result) {
        if (result.hasErrors()) {
            Map<String,String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField,
                            error -> Optional.ofNullable(error.getDefaultMessage()).orElse("Invalid value")));
            return ResponseEntity.badRequest().body(errors);
        }
        return new ResponseEntity<>("Thêm sinh viên thành công", HttpStatus.CREATED);
    }
}
