package com.accenture.challenge.franchise.franchise_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Schema(description = "Nombre del producto", example = "Hamburguesa Especial")
    private String name;

    @Min(value = 1, message = "El stock inicial debe ser al menos 1")
    @Schema(description = "Cantidad inicial de inventario", example = "50")
    @NotNull(message = "El stock es obligatorio")
    private Integer stock;
}