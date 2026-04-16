package com.example.bai1.controller;

import com.example.bai1.model.entity.Doctor;
import com.example.bai1.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @GetMapping
    public ResponseEntity<?> findAllDoctors() {
        List <Doctor> doctorList = doctorService.findAllDoctors();
        if (doctorList.isEmpty()) {
            return new ResponseEntity<>("Danh sách rỗng",HttpStatus.NO_CONTENT);
        }else  {
            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<?> saveDoctor (@RequestBody Doctor doctor){
        Doctor doctorNew = doctorService.addDoctor(doctor);
        if(doctorNew!=null){
            return new ResponseEntity<>("Doctor added successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Doctor not added", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor (@PathVariable Long id, @RequestBody Doctor doctor){
        try {
            Doctor updateDoctor = doctorService.updateDoctor(id, doctor);
            if (updateDoctor != null) {
                return new ResponseEntity<>(updateDoctor, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Doctor not updated", HttpStatus.BAD_REQUEST);
            }
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteDoctor (@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
    }

}
