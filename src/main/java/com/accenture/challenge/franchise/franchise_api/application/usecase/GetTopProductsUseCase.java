package com.accenture.challenge.franchise.franchise_api.application.usecase;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.application.dto.TopProductResponse;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository; // Asegura esta importación

import reactor.core.publisher.Flux;

@Service
public class GetTopProductsUseCase {

    private final BranchRepository branchRepository;
    private final ProductRepository productRepository; // Nuevo campo para cumplir con el test

    // Constructor actualizado con ambos repositorios
    public GetTopProductsUseCase(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    public Flux<TopProductResponse> execute(String franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId)
                .map(branch -> {
                    return branch.getProducts().stream()
                            .max(Comparator.comparingInt(p -> p.getStock()))
                            .map(p -> new TopProductResponse(branch.getName(), p.getName(), p.getStock()))
                            .orElse(new TopProductResponse(branch.getName(), "Sin productos", 0));
                });
    }
}