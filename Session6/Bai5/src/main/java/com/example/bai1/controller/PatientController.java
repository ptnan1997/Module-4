package com.example.bai1.controller;

import com.example.bai1.DTO.PaginationResponse;
import com.example.bai1.model.entity.Patient;
import com.example.bai1.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        Patient newPatient = patientService.save(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable int id) {
        return new ResponseEntity<>("Patient with id: " + id + " was deleted", HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchPatients(
            @RequestParam(required = false) String patientName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Patient> patientPage = patientService.searchPatient(patientName, page, size);

        PaginationResponse<Patient> response = new PaginationResponse<>(
                patientPage.getContent(),
                patientPage.getTotalPages(),
                patientPage.getTotalElements(),
                patientPage.getNumber()
        );

        // 👉 Nếu người dùng không nhập gì
        if (patientName.trim().isEmpty()) {
            return new ResponseEntity<>("Patient name is empty", HttpStatus.BAD_REQUEST);
        }

        return new  ResponseEntity<>(response, HttpStatus.OK);
    }


}
