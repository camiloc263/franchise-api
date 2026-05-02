package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository; // Nueva importación

import reactor.core.publisher.Mono;

@Service
public class DeleteProductUseCase {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository; // Nuevo campo

    // Constructor actualizado con ambas dependencias
    public DeleteProductUseCase(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    public Mono<Void> execute(String branchId, String productId) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La sucursal con ID " + branchId + " no existe.")))
                .flatMap(branch -> 
                    // En un enfoque desacoplado, eliminamos del repositorio de productos
                    productRepository.deleteById(productId)
                )
                .then();
    }
}