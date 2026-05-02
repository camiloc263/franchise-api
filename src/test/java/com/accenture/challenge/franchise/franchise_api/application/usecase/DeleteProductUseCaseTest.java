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
        useCase = new DeleteProductUseCase(branchRepository, productRepository);
    }

    @Test
    void execute_ShouldRemoveProductSuccessfully() {

        String branchId = "b1";
        String productIdToDelete = "p1";
        List<Product> products = new ArrayList<>();

        Product product1 = Product.builder()
                .id("id-1")
                .stock(10)
                .branchId("branch-1")
                .version(0L)
                .build();

        Product product2 = Product.builder()
                .id("id-2")
                .name("Otro producto")
                .stock(20)
                .branchId("branch-1")
                .version(0L)
                .build();

        Branch branch = new Branch(branchId, "Sucursal A", "f1", products);

        when(branchRepository.findById(branchId)).thenReturn(Mono.just(branch));
        when(productRepository.deleteById(productIdToDelete)).thenReturn(Mono.empty());

        Mono<Void> result = useCase.execute(branchId, productIdToDelete);

        StepVerifier.create(result)
                .verifyComplete();
    }
}