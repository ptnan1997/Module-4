package com.example.demo.service;

import com.example.demo.DTO.EmployeeCreateDTO;
import com.example.demo.Entity.Department;
import com.example.demo.Entity.Employee;
import com.example.demo.Exception.DuplicateResourceException;
import com.example.demo.Exception.FileValidationException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepos;
import com.example.demo.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.UUID;




@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepos departmentRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee create(EmployeeCreateDTO employeecreateDTO) {
        // Kiểm tra phòng ban
        Department department = departmentRepository.findById(employeecreateDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng ban với Id = " + employeecreateDTO.getDepartmentId()) );
        // Kiểm tra trùng email
        if (employeeRepository.existsByEmail(employeecreateDTO.getEmail())) {
            throw new DuplicateResourceException("Email đã bị trùng");
        }
        Employee employee = new Employee();
        employee.setFullName(employeecreateDTO.getFullName());
        employee.setEmail(employeecreateDTO.getEmail());
        employee.setPhone(employeecreateDTO.getPhone());
        employee.setSalary(employeecreateDTO.getSalary());
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }
    public Employee updateAvatar (Long id, MultipartFile file){
        // Check employee tồn tại
        Employee emp = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Không tìm thấy nhân viên với Id="+ id) );
        // Check file rong
        if (file.isEmpty()) {
            throw new FileValidationException("File is empty");
        }
        // Check size of file
        if (file.getSize() > 2 * 1024 * 1024) {
            throw new FileValidationException("File is too large");
        }
        // Check đuôi file
        String fileName = file.getOriginalFilename();
        if (fileName == null|| !(fileName.endsWith(".jpg")|| fileName.endsWith(".png")|| fileName.endsWith(".jpeg"))) {
            throw new FileValidationException("Chỉ chấp nhận file .jpg, .png, .jpeg");
        }
        // Tạo folder và lưu file vào
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads";
            Path uploadPath = Paths.get(uploadDir);

            // ✔ tạo folder chắc chắn
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String cleanName = Paths.get(fileName).getFileName().toString();

            String newFileName = UUID.randomUUID() + "_" + cleanName;

            Path filePath = uploadPath.resolve(newFileName);

            System.out.println("Saving to: " + filePath.toAbsolutePath());

            file.transferTo(filePath.toFile());

            emp.setAvatarUrl("/uploads/" + newFileName);

            return employeeRepository.save(emp);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage());
        }
    }
}
