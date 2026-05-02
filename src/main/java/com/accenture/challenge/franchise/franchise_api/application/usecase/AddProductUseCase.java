package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateProductRequest;
import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class AddProductUseCase {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    // Corrección de la inicialización: asegurar el uso de 'this'
    public AddProductUseCase(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    public Mono<Void> execute(String branchId, CreateProductRequest request) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La sucursal no existe.")))
                .flatMap(branch -> {
                    Product newProduct = new Product(
                        null, 
                        request.getName(), 
                        request.getStock(), 
                        branchId
                    );
                    return productRepository.save(newProduct);
                })
                .then(); // Corrección de tipo: Convierte Mono<Product> a Mono<Void>
    }
}