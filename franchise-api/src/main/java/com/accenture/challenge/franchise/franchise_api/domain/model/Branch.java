package com.accenture.challenge.franchise.franchise_api.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder; 
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class Branch {
    private String id;
    private String name;
    private String franchiseId;
    @Builder.Default 
    private List<Product> products = new ArrayList<>(); 

  
    public void updateName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la sucursal no puede estar vacío.");
        }
        this.name = newName;
    }

    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(product);
    }

    public void removeProduct(String productName) {
        if (this.products != null) {
            this.products.removeIf(p -> p.getName().equalsIgnoreCase(productName));
        }
    }
}