package com.accenture.challenge.franchise.franchise_api.infrastructure.mapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.BranchDocument;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.ProductDocument;

@Component
public class BranchMapper {

    // Dominio -> Infraestructura (Para guardar en MongoDB)
    public BranchDocument toDocument(Branch domain) {
        if (domain == null) return null;
        
        return BranchDocument.builder()
                .id(domain.getId())
                .name(domain.getName())
                .franchiseId(domain.getFranchiseId())
                .products(domain.getProducts() != null ? 
                        domain.getProducts().stream()
                              .map(this::toProductDocument)
                              .collect(Collectors.toList()) 
                        : new ArrayList<>())
                .build();
    }

    // Infraestructura -> Dominio (Para leer de MongoDB)
    public Branch toDomain(BranchDocument doc) {
        if (doc == null) return null;

        return new Branch(
                doc.getId(),
                doc.getName(),
                doc.getFranchiseId(),
                doc.getProducts() != null ? 
                        doc.getProducts().stream()
                              .map(pDoc -> toProductDomain(pDoc, doc.getId())) // Pasamos el ID de la sucursal
                              .collect(Collectors.toList()) 
                        : new ArrayList<>()
        );
    }

    private ProductDocument toProductDocument(Product domain) {
        if (domain == null) return null;
        return ProductDocument.builder()
                .id(domain.getId())
                .name(domain.getName())
                .stock(domain.getStock())
                .branchId(domain.getBranchId()) // Mapeamos el nuevo campo
                .build();
    }

    // Actualizado para usar el constructor de 4 argumentos
    private Product toProductDomain(ProductDocument doc, String branchId) {
        if (doc == null) return null;
        return new Product(
                doc.getId(), 
                doc.getName(), 
                doc.getStock(), 
                branchId // Ahora enviamos los 4 parámetros requeridos
        );
    }
}