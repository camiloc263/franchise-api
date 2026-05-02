package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.FranchiseDocument;

public interface FranchiseMongoRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
}