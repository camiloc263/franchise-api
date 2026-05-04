package com.accenture.challenge.franchise.franchise_api.domain.repository;

import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchRepository {
    
    Mono<Branch> save(Branch branch);
    
    Mono<Branch> findById(String id);
    
    Flux<Branch> findByFranchiseId(String franchiseId);
    
    Mono<Void> deleteById(String id);
}