package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;
import com.accenture.challenge.franchise.franchise_api.domain.repository.FranchiseRepository;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository.FranchiseMongoRepository;
import com.accenture.challenge.franchise.franchise_api.infrastructure.mapper.FranchiseMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Primary
@RequiredArgsConstructor
public class FranchiseRepositoryAdapter implements FranchiseRepository {

    private final FranchiseMongoRepository mongoRepository;
    private final FranchiseMapper mapper;

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return Mono.just(franchise)
                .map(mapper::toDocument)
                .flatMap(mongoRepository::save)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Franchise> findById(String id) {
        return mongoRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Franchise> findAll() { // Implementación del método faltante
        return mongoRepository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) { // Implementación del método faltante
        return mongoRepository.deleteById(id);
    }
}