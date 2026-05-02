package com.accenture.challenge.franchise.franchise_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto para la actualización de existencias de un producto")
public class UpdateStockRequest {

    @Min(value = 0, message = "El stock actual no puede ser negativo")
    @Schema(
        description = "Valor del stock antes de la actualización (útil para validaciones de consistencia)",
        example = "10"
    )
    private int stock;

    @Min(value = 0, message = "El nuevo stock no puede ser negativo")
    @Schema(
        description = "Nueva cantidad total de productos disponibles",
        example = "25",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private int newStock;
}