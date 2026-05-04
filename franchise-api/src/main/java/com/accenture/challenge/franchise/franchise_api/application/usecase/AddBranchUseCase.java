package com.accenture.challenge.franchise.franchise_api.application.usecase;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateBranchRequest;
import com.accenture.challenge.franchise.franchise_api.common.exception.ResourceNotFoundException;
import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.FranchiseRepository; // O FranchiseNotFoundException si la llamaste así

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor 
public class AddBranchUseCase {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public Mono<Void> execute(String franchiseId, CreateBranchRequest request) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("La franquicia con ID " + franchiseId + " no existe.")))
                .flatMap(existingFranchise -> {

                    Branch newBranch = new Branch(null, request.getName(), franchiseId, new ArrayList<>());
                    return branchRepository.save(newBranch);
                })
                .then(); 
    }
}