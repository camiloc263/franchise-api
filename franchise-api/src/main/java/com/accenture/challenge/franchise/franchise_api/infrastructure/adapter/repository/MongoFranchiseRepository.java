package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.FranchiseDocument;


@Repository
public interface MongoFranchiseRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
}