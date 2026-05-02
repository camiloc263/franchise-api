package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class UpdateProductStockUseCase {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    // ACTUALIZACIÓN: Constructor con ambos repositorios
    public UpdateProductStockUseCase(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    // ACTUALIZACIÓN: Método execute ahora solo requiere productId y el nuevo stock
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