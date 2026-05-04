package com.accenture.challenge.franchise.franchise_api.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor; 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Franchises", description = "Gestión centralizada de franquicias y sus dependencias")
@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor 
public class FranchiseController {

    private final GetTopProductsUseCase getTopProductsUseCase;
    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final AddBranchUseCase addBranchUseCase;
    private final UpdateFranchiseNameUseCase updateFranchiseNameUseCase;


    @Operation(summary = "Crear una nueva franquicia")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Franquicia creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public Mono<ResponseEntity<Franchise>> createFranchise(@Valid @RequestBody CreateFranchiseRequest request) { // Añadimos @Valid
        return createFranchiseUseCase.execute(request)
                .map(franchise -> ResponseEntity.status(HttpStatus.CREATED).body(franchise));
    }

    @Operation(summary = "Agregar sucursal a una franquicia")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Sucursal agregada correctamente"),
        @ApiResponse(responseCode = "404", description = "Franquicia no encontrada")
    })
    @PostMapping("/{id}/branches")
    @ResponseStatus(HttpStatus.CREATED) 
    public Mono<Void> addBranchToFranchise(
            @Parameter(description = "ID de la franquicia", example = "64b7f1...") @PathVariable("id") String franchiseId,
            @Valid @RequestBody CreateBranchRequest request) {
        return addBranchUseCase.execute(franchiseId, request);
    }

    @Operation(summary = "Obtener productos con mayor stock por sucursal (Requerimiento 7)")
    @GetMapping("/{id}/top-products")
    public Flux<TopProductResponse> getTopProductsByBranch(
            @Parameter(description = "ID de la franquicia a consultar") @PathVariable("id") String franchiseId) {
        return getTopProductsUseCase.execute(franchiseId);
    }

    @Operation(summary = "Actualizar nombre de la franquicia")
    @PatchMapping("/{id}/name")
    public Mono<Void> updateFranchiseName(
            @Parameter(description = "ID de la franquicia a modificar") @PathVariable("id") String franchiseId,
            @Valid @RequestBody UpdateNameRequest request) {
        return updateFranchiseNameUseCase.execute(franchiseId, request.getName());
    }
}