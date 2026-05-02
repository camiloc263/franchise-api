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
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class UpdateProductStockUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BranchRepository branchRepository;

    private UpdateProductStockUseCase useCase;

    @BeforeEach
    void setUp() {
        // Ahora el constructor coincide con tu clase de aplicación
        useCase = new UpdateProductStockUseCase(productRepository, branchRepository);
    }

    @Test
    void execute_ShouldUpdateStockSuccessfully() {
        // GIVEN
        String productId = "p1";
        int newStock = 500;
        Product product = new Product(productId, "Burger", 10, "b1");

        // Configuramos los mocks para el flujo reactivo
        when(productRepository.findById(productId)).thenReturn(Mono.just(product));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));

        // WHEN: Llamamos al método con 2 argumentos (String, int)
        Mono<Void> result = useCase.execute(productId, newStock);

        // THEN
        StepVerifier.create(result)
                .verifyComplete();
        
        // Verificación adicional de comportamiento
        verify(productRepository).save(argThat(p -> p.getStock() == 500));
    }
}