package com.accenture.challenge.franchise.franchise_api.domain.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Product {
    private String id;
    private String name;
    private Integer stock;
    private String branchId;
    private Long version;

    public void updateStock(int newStock) {
        if (newStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock = newStock;
    }
}