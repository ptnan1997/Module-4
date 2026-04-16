package com.example.bai1.service;

import com.example.bai1.model.entity.Patient;
import com.example.bai1.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
    public void delete(Long id) {
        patientRepository.delete(patientRepository.getReferenceById(id));
    }
    public Page<Patient> findAllAndSearch(Pageable pageable, String patientName) {
            return patientRepository.findAll(pageable);
    }
    public Page<Patient> searchPatient (String patientName,int page,int size ){
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.ASC, "fullName").descending()
        );
        if (patientName.isBlank()){
            return patientRepository.findAll(pageRequest);
        }
        else {
           return patientRepository.findByFullNameContaining(pageRequest, patientName);
        }
    }
}
