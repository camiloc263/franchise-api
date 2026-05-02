package com.accenture.challenge.franchise.franchise_api.application.usecase;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.accenture.challenge.franchise.franchise_api.domain.model.Branch;
import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class DeleteProductUseCaseTest {

    private final BranchRepository branchRepository = Mockito.mock(BranchRepository.class);
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private DeleteProductUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteProductUseCase(productRepository, branchRepository);
    }

    @Test
    void execute_ShouldRemoveProductSuccessfully() {
        // GIVEN
        String branchId = "b1";
        String productIdToDelete = "p1"; // Generalmente se elimina por ID en UseCases profesionales
        
        List<Product> products = new ArrayList<>();
        // CORRECCIÓN: Usamos el constructor de 4 argumentos: id, name, stock, branchId
        products.add(new Product("p1", "Soda", 50, branchId)); 
        products.add(new Product("p2", "Burger", 20, branchId));
        
        Branch branch = new Branch(branchId, "Sucursal A", "f1", products);

        // Mocking
        when(branchRepository.findById(branchId)).thenReturn(Mono.just(branch));
        when(productRepository.deleteById(productIdToDelete)).thenReturn(Mono.empty());

        // WHEN
        Mono<Void> result = useCase.execute(branchId, productIdToDelete);

        // THEN
        StepVerifier.create(result)
                .verifyComplete();
    }
}