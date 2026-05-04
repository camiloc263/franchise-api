package com.accenture.challenge.franchise.franchise_api.domain.repository;

import com.accenture.challenge.franchise.franchise_api.domain.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductRepository {
    
    Mono<Product> save(Product product);
    
    Mono<Product> findById(String id);
    
    Flux<Product> findAll();
    
    Flux<Product> findByBranchId(String branchId);
    
    Mono<Void> deleteById(String id);

   Mono<Product> findTopByBranchId(String branchId);
}