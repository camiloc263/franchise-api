package com.accenture.challenge.franchise.franchise_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFranchiseRequest {

@NotBlank(message = "El nombre de la franquicia es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Schema(description = "Nombre de la nueva franquicia", example = "Restaurantes Gp", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    
}