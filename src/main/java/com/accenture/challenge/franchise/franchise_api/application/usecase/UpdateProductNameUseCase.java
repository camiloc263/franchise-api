package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateProductNameUseCase {
    private final ProductRepository productRepository; 

    public Mono<Void> execute(String productId, String newName) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Producto no encontrado")))
                .flatMap(product -> {
                    product.setName(newName); 
                    return productRepository.save(product);
                })
                .then(); 
}
}