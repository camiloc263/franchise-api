package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository.ProductMongoRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductMongoRepository repository;

    @Override
    public Mono<Product> save(Product product) {
        return repository.save(product);
    }

    

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}