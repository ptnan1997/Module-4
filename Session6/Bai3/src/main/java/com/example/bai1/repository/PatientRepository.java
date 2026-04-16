package com.example.bai1.repository;

import com.example.bai1.model.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByFullNameContaining(Pageable pageable, String patientName);
}
