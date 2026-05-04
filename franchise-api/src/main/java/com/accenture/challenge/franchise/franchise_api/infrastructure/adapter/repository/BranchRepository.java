package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;

import reactor.core.publisher.Flux;

public interface BranchRepository extends ReactiveMongoRepository<Branch, String> {

    Flux<Branch> findByFranchiseId(String franchiseId);

}
