package com.accenture.challenge.franchise.franchise_api.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class Branch {
    private String id;
    private String name;
    private String franchiseId;
    private List<Product> products = new ArrayList<>(); // Inicialización por defecto

    // Tu método de dominio para agregar productos de forma segura
    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(product);
    }

    public void removeProduct(String productName) {
    if (this.products != null) {
        // Filtramos la lista para remover el producto por nombre
        this.products.removeIf(p -> p.getName().equalsIgnoreCase(productName));
    }
}
}