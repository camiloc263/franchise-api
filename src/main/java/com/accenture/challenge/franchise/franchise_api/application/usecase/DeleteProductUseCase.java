package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository; // Nueva importación

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository; // Nuevo campo

    
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