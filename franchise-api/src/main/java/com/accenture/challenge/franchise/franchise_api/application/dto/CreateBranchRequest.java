package com.accenture.challenge.franchise.franchise_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBranchRequest {
    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    @Schema(description = "Nombre de la sucursal a agregar", example = "Sede Pasto Centro", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}