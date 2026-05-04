package com.accenture.challenge.franchise.franchise_api.application.usecase;

import java.util.ArrayList; // Nombre corregido

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateBranchRequest;
import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.FranchiseRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class AddBranchUseCaseTest {

    private final BranchRepository branchRepository = Mockito.mock(BranchRepository.class);
    private final FranchiseRepository franchiseRepository = Mockito.mock(FranchiseRepository.class);

    // Inyectamos ambos como se ve en tu estructura
    private final AddBranchUseCase useCase = new AddBranchUseCase(branchRepository, franchiseRepository);

    @Test
    void execute_ShouldAddBranchSuccessfully() {
        // GIVEN
        String franchiseId = "f1";
        CreateBranchRequest request = new CreateBranchRequest();
        request.setName("Sucursal Centro");

        Franchise franchise = new Franchise(franchiseId, "Franquicia A", new ArrayList<>());
        // Creamos un objeto Branch para que el save del branchRepository no devuelva
        // null
        Branch branch = new Branch("b1", "Sucursal Centro", franchiseId, new ArrayList<>());

        // MOCK 1: Repositorio de Franquicias (para encontrar la franquicia)
        when(franchiseRepository.findById(franchiseId)).thenReturn(Mono.just(franchise));

        // MOCK 2: Repositorio de Sucursales (para guardar la nueva sucursal)
        // ESTA ES LA PARTE QUE PROBABLEMENTE FALTA Y CAUSA EL NULL
        when(branchRepository.save(any(Branch.class))).thenReturn(Mono.just(branch));

        // WHEN & THEN
        StepVerifier.create(useCase.execute(franchiseId, request))
                .verifyComplete();
    }
}