package com.example.bai1.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreateDTO {
    @NotBlank
    private String productName;
    @NotNull

    private Double price;
    @NotNull
    private int quantity;
}