package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateProductRequest;
import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddProductUseCase {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

   
    public Mono<Void> execute(String branchId, CreateProductRequest request) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La sucursal no existe.")))
                .flatMap(branch -> {
                    Product newProduct = Product.builder()
                            .name(request.getName())
                            .stock(request.getStock())
                            .branchId(branchId)
                            .build();
                    return productRepository.save(newProduct);
                })
                .then();
    }
}