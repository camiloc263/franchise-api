package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.repository.BranchMongoRepository;
import com.accenture.challenge.franchise.franchise_api.infrastructure.mapper.BranchMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository // <--- ¡ESTO ES LO QUE SPRING ESTÁ BUSCANDO!
@RequiredArgsConstructor
public class BranchRepositoryAdapter implements BranchRepository {

    private final BranchMongoRepository mongoRepository;
    private final BranchMapper mapper;

    @Override
    public Mono<Branch> save(Branch branch) {
        return Mono.just(branch)
                .map(mapper::toDocument)
                .flatMap(mongoRepository::save)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Branch> findById(String id) {
        return mongoRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Branch> findByFranchiseId(String franchiseId) {
        return mongoRepository.findByFranchiseId(franchiseId)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoRepository.deleteById(id);
    }
}