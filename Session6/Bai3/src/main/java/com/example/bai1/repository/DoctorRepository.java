package com.example.bai1.repository;

import com.example.bai1.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;





public interface DoctorRepository extends JpaRepository<Doctor,Long> {

}
