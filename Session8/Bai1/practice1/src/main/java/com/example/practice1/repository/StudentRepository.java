package com.example.practice1.repository;

import com.example.practice1.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentRepository {
    List<Student> students = new ArrayList<>();
    public StudentRepository() {
        students.add(new Student(1, "Nguyễn Văn A"));
        students.add(new Student(2, "Nguyễn Văn B"));
        students.add(new Student(3, "Nguyễn Văn C"));
        students.add(new Student(4, "Nguyễn Văn D"));
        students.add(new Student(5, "Nguyễn Văn F"));
    }
    public List<Student> findAll() {
        return students;
    }

    public Student findById(int id) {
        return students.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void create(Student employee) {
        students.add(employee);
    }

    public void delete(int id) {
        students.removeIf(e -> e.getId() == id);
    }
}
