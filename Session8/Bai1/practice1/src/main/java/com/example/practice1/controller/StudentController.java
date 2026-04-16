package com.example.practice1.controller;

import com.example.practice1.Exception.StudentNotFoundException;
import com.example.practice1.model.Student;
import com.example.practice1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        for (Student student : studentRepository.findAll()) {
            if(student.getId() == id) {
                return student;
            }
        }
        throw new StudentNotFoundException("Sinh viên với ID"+ id + "không tồn tại");
    }
}
