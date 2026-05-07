package com.example.bai1.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.bai1.exception.CustomException;
import com.example.bai1.model.dto.request.EmployeeCreateDTO;
import com.example.bai1.model.entity.Employee;
import com.example.bai1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private Cloudinary  cloudinary;

    public Employee addEmployee(EmployeeCreateDTO employeeCreateDTO) {
        if(employeeCreateDTO.getAvatarFile().isEmpty()){
            throw new CustomException("File avatar cannot be empty");
        }
        try {
            Map<String,Object> upload = cloudinary
                    .uploader()
                    .upload(employeeCreateDTO.getAvatarFile().getBytes(), ObjectUtils.emptyMap());
            Employee employee = new Employee();
            employee.setEmail(employeeCreateDTO.getEmail());
            employee.setAvatarUrl(upload.get("secure_url").toString());
            employee.setDepartment(employeeCreateDTO.getDepartment());
            employee.setFullName(employeeCreateDTO.getFullName());
            return employeeRepository.save(employee);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}