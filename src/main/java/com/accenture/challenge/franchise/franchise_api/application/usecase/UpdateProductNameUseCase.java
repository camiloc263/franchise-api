package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateProductNameUseCase {
    private final BranchRepository repository;

    public Mono<Void> execute(String branchId, String oldName, String newName) {
        return repository.findById(branchId)
                .flatMap(branch -> {
                    branch.getProducts().stream()
                            .filter(p -> p.getName().equals(oldName))
                            .findFirst()
                            .ifPresent(p -> p.setName(newName));
                    return repository.save(branch);
                })
                .then();
    }
}