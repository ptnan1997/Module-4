package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EmployeeCreateDTO {
    @NotNull(message = "Tên không được để trống")
    private String fullName;
    @Email(message="Email không đúng định dạng ")
    private String email;
    @Pattern(regexp = "^(03 05 07 08 09)\\d{8}$", message = "SDT không hợp lệ")
    private String phone;
    @Min(value = 5000000,message = "Lương tối thiểu 5 triệu")
    private Double salary;
    @NotNull (message = "Phòng ban không được bỏ trống")
    private Long departmentId;

}
