package com.accenture.challenge.franchise.franchise_api.application.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockRequest {
    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;
    private int newStock;
}