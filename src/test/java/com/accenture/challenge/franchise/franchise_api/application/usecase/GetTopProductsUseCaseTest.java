package com.accenture.challenge.franchise.franchise_api.application.usecase;

import com.accenture.challenge.franchise.franchise_api.application.dto.TopProductResponse;
import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository; // Importación necesaria
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class GetTopProductsUseCaseTest {

    private final BranchRepository branchRepository = Mockito.mock(BranchRepository.class);
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class); // Mock adicional
    private GetTopProductsUseCase useCase;

    @BeforeEach
    void setUp() {
        // Inicialización con el constructor que espera ambos repositorios
        useCase = new GetTopProductsUseCase(productRepository, branchRepository);
    }

    @Test
    void execute_ShouldReturnFluxOfTopProducts() {
        // GIVEN
        String franchiseId = "f123";
        String branchAId = "b1";
        String branchBId = "b2";
        
        // CORRECCIÓN: Constructor de 4 argumentos (id, name, stock, branchId)
        Product p1 = new Product("p1", "LowStock", 10, branchAId);
        Product p2 = new Product("p2", "HighStock", 150, branchAId); 
        List<Product> productsA = Arrays.asList(p1, p2);
        Branch branchA = new Branch(branchAId, "Sucursal A", franchiseId, productsA);

        Product p3 = new Product("p3", "OnlyItem", 45, branchBId);
        List<Product> productsB = Arrays.asList(p3);
        Branch branchB = new Branch(branchBId, "Sucursal B", franchiseId, productsB);

        when(branchRepository.findByFranchiseId(franchiseId)).thenReturn(Flux.just(branchA, branchB));

        // WHEN: Ejecutamos el reporte
        Flux<TopProductResponse> result = useCase.execute(franchiseId);

        // THEN: Verificamos los resultados esperados
        StepVerifier.create(result)
                .expectNextMatches(response -> 
                    response.getBranchName().equals("Sucursal A") && 
                    response.getProductName().equals("HighStock") && 
                    response.getStock() == 150)
                .expectNextMatches(response -> 
                    response.getBranchName().equals("Sucursal B") && 
                    response.getProductName().equals("OnlyItem") && 
                    response.getStock() == 45)
                .verifyComplete();
    }
}