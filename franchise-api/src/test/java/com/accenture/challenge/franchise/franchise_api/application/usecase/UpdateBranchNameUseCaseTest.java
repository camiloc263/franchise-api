package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class UpdateBranchNameUseCaseTest {

    private final BranchRepository repository = Mockito.mock(BranchRepository.class);
    private final UpdateBranchNameUseCase useCase = new UpdateBranchNameUseCase(repository);

    @Test
    void execute_ShouldUpdateBranchNameSuccessfully() {
        String branchId = "b1";
        Branch branch = new Branch(branchId, "Nombre Antiguo", "f1", new java.util.ArrayList<>());

        when(repository.findById(branchId)).thenReturn(Mono.just(branch));
        when(repository.save(any(Branch.class))).thenReturn(Mono.just(branch));

        StepVerifier.create(useCase.execute(branchId, "Nombre Nuevo"))
                .verifyComplete();
    }
}