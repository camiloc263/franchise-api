package com.accenture.challenge.franchise.franchise_api.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.ProductDocument;

/**
 * Mapper para la entidad Product.
 * Transforma datos entre el modelo de dominio y el documento de persistencia.
 */
@Component
public class ProductMapper {


  
   public Product toDomain(ProductDocument document) {
    if (document == null) {
        return null;
    }
    
    return Product.builder()
            .id(document.getId())
            .name(document.getName())
            .stock(document.getStock())
            .branchId(document.getBranchId())
            .version(document.getVersion()) 
            .build();
}

public ProductDocument toDocument(Product product) {
    if (product == null) {
        return null;
    }
    return ProductDocument.builder()
            .id(product.getId())
            .name(product.getName())
            .stock(product.getStock())
            .branchId(product.getBranchId())
            .version(product.getVersion())
            .build();
}
}