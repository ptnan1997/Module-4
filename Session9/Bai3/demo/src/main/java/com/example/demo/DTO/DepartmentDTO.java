package com.example.demo.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DepartmentDTO {
    @NotNull(message = "Tên phòng ban không được để trống")
    @Size(min = 5, max = 50, message = "Độ dài bắt buộc từ 5 - 50")
    private String name;

    @Size (max = 100, message = "Mô tả tối đa 100 ký tự")
    private String description;


}
