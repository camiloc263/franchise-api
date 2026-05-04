package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.accenture.challenge.franchise.franchise_api.domain.model.Product;

public interface ProductMongoRepository extends ReactiveMongoRepository<Product, String> {

}