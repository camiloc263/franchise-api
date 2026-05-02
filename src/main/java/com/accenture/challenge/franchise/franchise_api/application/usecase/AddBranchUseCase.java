package com.accenture.challenge.franchise.franchise_api.application.usecase;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateBranchRequest;
import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.FranchiseRepository;

import reactor.core.publisher.Mono;

@Service
public class AddBranchUseCase {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public AddBranchUseCase(BranchRepository branchRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    public Mono<Void> execute(String franchiseId, CreateBranchRequest request) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("La franquicia con ID " + franchiseId + " no existe.")))
                .flatMap(existingFranchise -> {
                    Branch newBranch = new Branch(null, request.getName(), franchiseId, new ArrayList<>());
                    return branchRepository.save(newBranch);
                })
                .then(); 
    }
}