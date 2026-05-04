package com.accenture.challenge.franchise.franchise_api.application.usecase;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;
import com.accenture.challenge.franchise.franchise_api.domain.repository.FranchiseRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class UpdateFranchiseNameUseCaseTest {

    private final FranchiseRepository repository = Mockito.mock(FranchiseRepository.class);
    private final UpdateFranchiseNameUseCase useCase = new UpdateFranchiseNameUseCase(repository);

    @Test
    void execute_ShouldUpdateNameSuccessfully() {
        String id = "f1";
        Franchise franchise = new Franchise(id, "Viejo Nombre", new ArrayList<>());

        when(repository.findById(id)).thenReturn(Mono.just(franchise));
        when(repository.save(any(Franchise.class))).thenReturn(Mono.just(franchise));

        StepVerifier.create(useCase.execute(id, "Nuevo Nombre"))
                .verifyComplete();
    }
}