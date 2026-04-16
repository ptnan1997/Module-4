package com.example.bai1.dto;

import com.example.bai1.validation.ViettelPhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class ParentDTO {
    @NotBlank (message = "Tên không được để trống")
    @Pattern(regexp = "^[A-Z].*$",message = "Tên viết hoa chữ cái đầu")
    private String parentName;
    @NotNull(message = "Số điện thoại không được để trống")
    @ViettelPhone
    private String phone;


}
