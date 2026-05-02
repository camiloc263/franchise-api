package com.accenture.challenge.franchise.franchise_api.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String name;
    @Min(value = 1, message = "El stock inicial debe ser al menos 1")
    private Integer stock;
}