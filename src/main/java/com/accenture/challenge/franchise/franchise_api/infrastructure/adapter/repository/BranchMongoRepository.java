package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.BranchDocument; // IMPORTANTE: Debe apuntar al Document

import reactor.core.publisher.Flux;

@Repository
// FÍJATE AQUÍ: Debe decir <BranchDocument, String>, NO <Branch, String>
public interface BranchMongoRepository extends ReactiveMongoRepository<BranchDocument, String> {
    
    // Y este método debe devolver un Flux de BranchDocument
    Flux<BranchDocument> findByFranchiseId(String franchiseId);
}