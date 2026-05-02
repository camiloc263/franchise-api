package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateFranchiseRequest;

import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;

import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

import com.accenture.challenge.franchise.franchise_api.infrastructure.controller.FranchiseController;

@WebFluxTest(controllers = FranchiseController.class, 
            excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration.class,
            })class FranchiseControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CreateFranchiseUseCase createFranchiseUseCase;

    // Debemos mockear todos los beans que el controlador inyecta por constructor
    @MockBean
    private GetTopProductsUseCase getTopProductsUseCase;
    @MockBean
    private AddBranchUseCase addBranchUseCase;
    @MockBean
    private UpdateFranchiseNameUseCase updateFranchiseNameUseCase;

    @Test
    void createFranchise_ShouldReturn201AndFranchise() {
        // 1. Preparar los datos
        CreateFranchiseRequest request = new CreateFranchiseRequest();
        request.setName("Franquicia Test");

        Franchise savedFranchise = Franchise.builder()
                .id("f-123")
                .name("Franquicia Test")
                .branches(new ArrayList<>())
                .build();

        // 2. Configurar el comportamiento del Mock
        Mockito.when(createFranchiseUseCase.execute(any(CreateFranchiseRequest.class)))
                .thenReturn(Mono.just(savedFranchise));

        // 3. Ejecutar y Verificar
        webTestClient.post()
                .uri("/api/franchises")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange() // Realiza la "petición"
                .expectStatus().isCreated() // Verifica el 201 Created
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo("f-123")
                .jsonPath("$.name").isEqualTo("Franquicia Test");
    }
}