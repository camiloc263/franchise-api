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
        // GIVEN
        CreateFranchiseRequest request = new CreateFranchiseRequest();
        request.setName("Nueva Franquicia");
        
        Franchise savedFranchise = new Franchise("generated-id", "Nueva Franquicia", new ArrayList<>());

        // Mockeamos el save para que devuelva un Mono con el objeto (aunque el usecase lo ignore)
        when(repository.save(any(Franchise.class))).thenReturn(Mono.just(savedFranchise));

        // WHEN: Ejecutamos el caso de uso
        Mono<Void> result = useCase.execute(request); // Cambiado a Mono<Void> para coincidir con tu código

        // THEN: Verificamos que el flujo se complete sin errores
        StepVerifier.create(result)
                .verifyComplete(); 
    }
}