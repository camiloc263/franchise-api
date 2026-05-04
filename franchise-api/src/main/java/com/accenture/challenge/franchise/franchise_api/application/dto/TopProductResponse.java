package com.accenture.challenge.franchise.franchise_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopProductResponse {
    @Schema(description = "Nombre de la sucursal", example = "Sede Norte")
    private String branchName;

    @Schema(description = "Producto con mayor stock en esta sucursal", example = "Refresco 500ml")
    private String productName;

    @Schema(description = "Cantidad de stock máximo encontrado", example = "120")
    private int stock;
}