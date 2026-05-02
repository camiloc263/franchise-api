package com.accenture.challenge.franchise.franchise_api.application.usecase;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateFranchiseRequest;
import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;
import com.accenture.challenge.franchise.franchise_api.domain.repository.FranchiseRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class CreateFranchiseUseCaseTest {

    private final FranchiseRepository repository = Mockito.mock(FranchiseRepository.class);
    private final CreateFranchiseUseCase useCase = new CreateFranchiseUseCase(repository);

    @Test
    void execute_ShouldCreateFranchiseSuccessfully() {
        CreateFranchiseRequest request = new CreateFranchiseRequest();
        request.setName("Nueva Franquicia");
        
        
        Franchise savedFranchise = new Franchise("generated-id", "Nueva Franquicia", new ArrayList<>());

        when(repository.save(any(Franchise.class))).thenReturn(Mono.just(savedFranchise));

        Mono<Franchise> result = useCase.execute(request); 

        StepVerifier.create(result)
                .expectNextMatches(franchise -> 
                        franchise.getId().equals("generated-id") && 
                        franchise.getName().equals("Nueva Franquicia")
                )
                .verifyComplete(); 
    }
}