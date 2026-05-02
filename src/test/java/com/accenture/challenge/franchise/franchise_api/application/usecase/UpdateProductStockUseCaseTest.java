package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.accenture.challenge.franchise.franchise_api.domain.model.Product;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class UpdateProductStockUseCaseTest {

    @Mock
    private ProductRepository productRepository;



    private UpdateProductStockUseCase useCase;

   @BeforeEach
    void setUp() {
        useCase = new UpdateProductStockUseCase(productRepository);
    }

    @Test
    void execute_ShouldUpdateStockSuccessfully() {
       
        String productId = "p1";
        int newStock = 500;
       Product existingProduct = Product.builder()
        .id("id-1") 
        .name("Hamburguesa") 
        .stock(10) 
        .branchId("branch-1") 
        .version(0L) 
        .build();

        when(productRepository.findById(productId)).thenReturn(Mono.just(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(existingProduct));

        Mono<Void> result = useCase.execute(productId, newStock);

        StepVerifier.create(result)
                .verifyComplete();
        
        verify(productRepository).save(argThat(p -> p.getStock() == 500));
    }
}