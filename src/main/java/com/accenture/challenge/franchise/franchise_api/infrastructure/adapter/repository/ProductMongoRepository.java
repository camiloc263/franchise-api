package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.ProductDocument;

import reactor.core.publisher.Flux;

public interface ProductMongoRepository extends ReactiveMongoRepository<ProductDocument, String> {
Flux<ProductDocument> findByBranchId(String branchId);
}