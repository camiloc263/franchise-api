package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.ProductDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductMongoRepository extends ReactiveMongoRepository<ProductDocument, String> {
    Mono<ProductDocument> findFirstByBranchIdOrderByStockDesc(String branchId);

    Flux<ProductDocument> findByBranchId(String branchId);
}