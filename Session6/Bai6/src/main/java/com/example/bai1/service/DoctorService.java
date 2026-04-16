package com.example.bai1.service;

import com.example.bai1.model.entity.Doctor;
import com.example.bai1.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }
    public Doctor addDoctor(Doctor doctor){
        doctorRepository.save(doctor);
        return doctor;
    }
    public Doctor updateDoctor(Long id,Doctor  doctor){
        Doctor doctorUpdate = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctorUpdate.setDoctorCode(doctor.getDoctorCode());
        doctorUpdate.setExperience(doctor.getExperience());
        doctorUpdate.setFullName(doctor.getFullName());
        doctorUpdate.setSpecilization(doctor.getSpecilization());

        return doctorRepository.save(doctorUpdate);
    }
    public void deleteDoctor(Long id){
        doctorRepository.delete(doctorRepository.getReferenceById(id));
    }
}
