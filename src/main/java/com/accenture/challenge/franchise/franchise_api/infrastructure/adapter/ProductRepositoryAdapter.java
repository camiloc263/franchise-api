package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository.ProductMongoRepository;
import com.accenture.challenge.franchise.franchise_api.infrastructure.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Primary 
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductMongoRepository mongoRepository;
    private final ProductMapper mapper;
    

    @Override
    public Mono<Product> save(Product product) {
        return Mono.just(product)
                .map(mapper::toDocument)
                .flatMap(mongoRepository::save)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Product> findById(String id) {
        return mongoRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Product> findAll() {
        return mongoRepository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Product> findByBranchId(String branchId) {
        return mongoRepository.findByBranchId(branchId)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoRepository.deleteById(id);
    }
}