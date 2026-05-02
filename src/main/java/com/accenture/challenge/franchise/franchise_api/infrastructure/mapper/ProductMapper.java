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

    /**
     * Convierte un documento de MongoDB al modelo de dominio.
     */
    public Product toDomain(ProductDocument document) {
        if (document == null) {
            return null;
        }
        return new Product(
            document.getId(),
            document.getName(),
            document.getStock(),
            document.getBranchId()
        );
    }

    /**
     * Convierte el modelo de dominio a un documento de MongoDB para persistencia.
     */
    public ProductDocument toDocument(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDocument(
            product.getId(),
            product.getName(),
            product.getStock(),
            product.getBranchId()
        );
    }
}