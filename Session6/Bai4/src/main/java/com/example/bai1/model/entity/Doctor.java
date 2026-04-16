package com.example.bai1.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
public class Doctor {
    public Doctor() {
    }
    public Doctor (String doctorCode, String fullName, String specilization, Integer experience) {
        this.doctorCode = doctorCode;
        this.fullName = fullName;
        this.specilization = specilization;
        this.experience = experience;
    }
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @Column (name = "doctor_code", nullable = false)
    private String doctorCode;
    @Getter
    @Setter
    @Column (name = "full_name", nullable = false)
    private String fullName;
    @Getter
    @Setter
    @Column (name = "specilization", nullable = false)
    private String specilization;
    @Getter
    @Setter
    @Column (name = "experience", nullable = false)
    private Integer experience;




}
