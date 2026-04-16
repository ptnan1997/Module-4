package com.example.bai1.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name= "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "patient_code",length = 100, nullable = false)
    private String patientCode;

    @Column (name = "full_name",length = 100, nullable = false)
    private String fullName;

    @Column (name = "phone",length = 100, nullable = false)
    private String phone;

    @Column (name = "address",length = 100, nullable = false)
    private String address;
    public Patient() {}
    public Patient(String patientCode, String fullName, String phone, String address) {
        this.patientCode = patientCode;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }
}
