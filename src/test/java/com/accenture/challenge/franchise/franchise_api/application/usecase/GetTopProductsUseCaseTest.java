package com.accenture.challenge.franchise.franchise_api.application.usecase;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.accenture.challenge.franchise.franchise_api.application.dto.TopProductResponse;
import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class GetTopProductsUseCaseTest {

    // 1. Cambiamos el mock al repositorio de Sucursales
    private final BranchRepository branchRepository = mock(BranchRepository.class);
    private final GetTopProductsUseCase useCase = new GetTopProductsUseCase(branchRepository);

    @Test
    void execute_shouldReturnTopProductsFromAllBranches() {
        // Given
        String franchiseId = "franchise-1";
        
        Product product1 = new Product("p1", "Product A", 100);
        Product product2 = new Product("p2", "Product B", 50);
        
        // Branch 1 tiene a Product A como top (100)
        Branch branch1 = new Branch("b1", "Branch 1", franchiseId, List.of(product1, product2));
        // Branch 2 tiene a Product C como top (200)
        Branch branch2 = new Branch("b2", "Branch 2", franchiseId, List.of(new Product("p3", "Product C", 200)));
        
        // 2. Mockeamos el findByFranchiseId para que devuelva el Flux de sucursales directamente
        when(branchRepository.findByFranchiseId(franchiseId)).thenReturn(Flux.just(branch1, branch2));

        // When
        Flux<TopProductResponse> result = useCase.execute(franchiseId);

        // Then
        StepVerifier.create(result)
                .recordWith(ArrayList::new) // Crea una lista interna para grabar los resultados
                .expectNextCount(2)         // Esperamos 2 productos top (uno por sucursal)
                .consumeRecordedWith(results -> {
                    assertThat(results).hasSize(2);
                    assertThat(results).allSatisfy(response -> {
                        assertThat(response.getStock()).isGreaterThan(0);
                        assertThat(response.getBranchName()).containsAnyOf("Branch 1", "Branch 2");
                    });
                })
                .verifyComplete();

        // Verificamos la llamada al nuevo repositorio
        verify(branchRepository).findByFranchiseId(franchiseId);
    }

    @Test
    void execute_shouldReturnEmptyWhenNoBranchesFound() { // Renombrado para reflejar la lógica real
        // Given
        String franchiseId = "non-existent";
        // Si no hay sucursales, retorna un Flux vacío
        when(branchRepository.findByFranchiseId(franchiseId)).thenReturn(Flux.empty());

        // When
        Flux<TopProductResponse> result = useCase.execute(franchiseId);

        // Then
        StepVerifier.create(result)
                .verifyComplete();

        verify(branchRepository).findByFranchiseId(franchiseId);
    }

    @Test
    void execute_shouldReturnEmptyWhenBranchHasNoProducts() {
        // Given
        String franchiseId = "franchise-1";
        
        // Una sucursal con una lista vacía de productos
        Branch branch1 = new Branch("b1", "Branch 1", franchiseId, List.of());

        when(branchRepository.findByFranchiseId(franchiseId)).thenReturn(Flux.just(branch1));

        // When
        Flux<TopProductResponse> result = useCase.execute(franchiseId);

        // Then
        StepVerifier.create(result)
                .verifyComplete();
                
        verify(branchRepository).findByFranchiseId(franchiseId);
    }
}