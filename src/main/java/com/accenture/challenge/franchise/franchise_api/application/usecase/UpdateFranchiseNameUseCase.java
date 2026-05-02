package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.domain.repository.FranchiseRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateFranchiseNameUseCase {
    private final FranchiseRepository repository;

    public Mono<Void> execute(String franchiseId, String newName) {
        return repository.findById(franchiseId)
                .flatMap(franchise -> {
                    // Usamos el método de dominio que ya creaste y que tiene validación
                    franchise.updateName(newName);
                    return repository.save(franchise);
                })
                .then();
    }
}