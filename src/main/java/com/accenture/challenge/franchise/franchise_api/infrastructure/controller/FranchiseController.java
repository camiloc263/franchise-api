package com.accenture.challenge.franchise.franchise_api.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateBranchRequest;
import com.accenture.challenge.franchise.franchise_api.application.dto.CreateFranchiseRequest;
import com.accenture.challenge.franchise.franchise_api.application.dto.TopProductResponse;
import com.accenture.challenge.franchise.franchise_api.application.dto.UpdateNameRequest;
import com.accenture.challenge.franchise.franchise_api.application.usecase.AddBranchUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.CreateFranchiseUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.GetTopProductsUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.UpdateFranchiseNameUseCase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final GetTopProductsUseCase getTopProductsUseCase;
    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final AddBranchUseCase addBranchUseCase;
    private final UpdateFranchiseNameUseCase updateFranchiseNameUseCase; // Nuevo Caso de Uso

    // Constructor actualizado con los 4 Casos de Uso
    public FranchiseController(GetTopProductsUseCase getTopProductsUseCase,
                               CreateFranchiseUseCase createFranchiseUseCase,
                               AddBranchUseCase addBranchUseCase,
                               UpdateFranchiseNameUseCase updateFranchiseNameUseCase) {
        this.getTopProductsUseCase = getTopProductsUseCase;
        this.createFranchiseUseCase = createFranchiseUseCase;
        this.addBranchUseCase = addBranchUseCase;
        this.updateFranchiseNameUseCase = updateFranchiseNameUseCase;
    }

    // Endpoint 1: Crear Franquicia
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createFranchise(@RequestBody CreateFranchiseRequest request) {
        return createFranchiseUseCase.execute(request);
    }

    // Endpoint 2: Agregar Sucursal a una Franquicia
    @PostMapping("/{id}/branches")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> addBranchToFranchise(@PathVariable("id") String franchiseId,
                                           @RequestBody CreateBranchRequest request) {
        return addBranchUseCase.execute(franchiseId, request);
    }

    // Endpoint 3: Obtener productos con mayor stock por sucursal
    @GetMapping("/{id}/top-products")
    public Flux<TopProductResponse> getTopProductsByBranch(@PathVariable("id") String franchiseId) {
        return getTopProductsUseCase.execute(franchiseId);
    }

    // Plus: Endpoint para actualizar el nombre de la franquicia
    @PatchMapping("/{id}/name")
    public Mono<Void> updateFranchiseName(@PathVariable("id") String franchiseId,
                                          @RequestBody UpdateNameRequest request) {
        return updateFranchiseNameUseCase.execute(franchiseId, request.getName());
    }
}