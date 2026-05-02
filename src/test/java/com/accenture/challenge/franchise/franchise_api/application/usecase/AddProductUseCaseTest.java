package com.accenture.challenge.franchise.franchise_api.application.usecase;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateProductRequest;
import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class AddProductUseCaseTest {

    private final BranchRepository branchRepository = Mockito.mock(BranchRepository.class);
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private AddProductUseCase useCase;

    @BeforeEach
    void setUp() {
        // Inicialización con ambos repositorios requeridos
        useCase = new AddProductUseCase(productRepository, branchRepository);
    }

    @Test
    void execute_ShouldAddProductSuccessfully() {
        // GIVEN
        String branchId = "123";
        CreateProductRequest request = new CreateProductRequest("Burger", 10);
        Branch branch = new Branch(branchId, "Sucursal A", "franq1", new ArrayList<>());
        

        Product savedProduct = Product.builder()
        .id("id-123") 
        .name("Hamburguesa")
        .stock(50) 
        .branchId("branch-1") 
        .version(0L) 
        .build();

        when(branchRepository.findById(branchId)).thenReturn(Mono.just(branch));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(savedProduct));

        // WHEN
        Mono<Void> result = useCase.execute(branchId, request);

        // THEN
        StepVerifier.create(result)
                .verifyComplete(); 
    }
}

