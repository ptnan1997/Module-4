package com.example.bai1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentDTO {

    @NotBlank (message = "Tên không được để trống")
    @Size(min = 5, max = 100)
    private String fullName;

    @NotBlank(message = "Chuyên ngành không được để trống")
    private String major;

    @NotNull(message = "Tuổi không được để trống")
    @Min (18)
    private int age;

    @NotNull(message = "GPA không được để trống")
    @DecimalMin ("0.0")
    @DecimalMax("10.0")
    private double gpa;

    @NotBlank(message = "Mã sinh viên không được để trống")
    @Pattern(regexp = "^SV\\d{4}$",message = "Mã sinh viên phải đúng định dạng SV****")
    private String studentCode;

    @Valid
    private ParentDTO  parent;


    public StudentDTO (){}
    public StudentDTO(String fullName, String major, int age, double gpa, String studentCode) {
        this.fullName = fullName;
        this.major = major;
        this.age = age;
        this.gpa = gpa;
        this.studentCode = studentCode;
    }

}
