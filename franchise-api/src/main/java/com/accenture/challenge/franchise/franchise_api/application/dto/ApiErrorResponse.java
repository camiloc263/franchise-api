package com.accenture.challenge.franchise.franchise_api.application.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Estructura estandar para respuestas de error de la API")
public class ApiErrorResponse {

    @Schema(example = "2026-05-02T14:30:00Z")
    private String timestamp;

    @Schema(example = "400")
    private int status;

    @Schema(example = "Bad Request")
    private String error;

    @Schema(example = "Error de validacion")
    private String message;

    @Schema(example = "/api/v1/franchises")
    private String path;

    @Schema(description = "Detalles de los campos que fallaron")
    private Map<String, String> details;
}