package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateProductStockUseCase {

    private final ProductRepository productRepository;

   
    public Mono<Void> execute(String productId, int newStock) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Producto no encontrado")))
                .flatMap(product -> {
                    product.updateStock(newStock); // Asegúrate que este método exista en tu dominio
                    return productRepository.save(product);
                })
                .then();
    }
}