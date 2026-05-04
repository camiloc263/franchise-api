package com.accenture.challenge.franchise.franchise_api.application.usecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.accenture.challenge.franchise.franchise_api.application.dto.TopProductResponse;
import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


class GetTopProductsUseCaseTest {

    private BranchRepository branchRepository;
    private ProductRepository productRepository;
    private GetTopProductsUseCase useCase;

    @BeforeEach
    void setUp() {
        branchRepository = Mockito.mock(BranchRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        useCase = new GetTopProductsUseCase(branchRepository, productRepository);
    }

    @Test
    void execute_ShouldReturnFluxOfTopProducts() {
        String franchiseId = "f1";

        // Configuración de Sucursales
        Branch branchA = Branch.builder().id("b1").name("Sucursal A").build();
        Branch branchB = Branch.builder().id("b2").name("Sucursal B").build();

        // Configuración de Productos Top
        Product topProductA = Product.builder().name("Producto Uno").stock(100).build();
        Product topProductB = Product.builder().name("Producto Tres").stock(200).build();

        // Mocks
        when(branchRepository.findByFranchiseId(franchiseId)).thenReturn(Flux.just(branchA, branchB));
        when(productRepository.findTopByBranchId("b1")).thenReturn(Mono.just(topProductA));
        when(productRepository.findTopByBranchId("b2")).thenReturn(Mono.just(topProductB));

        // Ejecución
        Flux<TopProductResponse> result = useCase.execute(franchiseId);

        // VERIFICACIÓN CORREGIDA
        StepVerifier.create(result)
                .expectNextMatches(response -> 
                    response.getBranchName().equals("Sucursal A") && 
                    response.getProductName().equals("Producto Uno") && 
                    response.getStock() == 100)
                .expectNextMatches(response -> 
                    response.getBranchName().equals("Sucursal B") && 
                    response.getProductName().equals("Producto Tres") && 
                    response.getStock() == 200) // Consumimos el segundo elemento que antes causaba el error
                .verifyComplete();
    }
}