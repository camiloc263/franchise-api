package com.accenture.challenge.franchise.franchise_api.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable; // Añadido para simplificar el constructor
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateProductRequest;
import com.accenture.challenge.franchise.franchise_api.application.dto.UpdateNameRequest;
import com.accenture.challenge.franchise.franchise_api.application.dto.UpdateStockRequest;
import com.accenture.challenge.franchise.franchise_api.application.usecase.AddProductUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.DeleteProductUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.UpdateBranchNameUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.UpdateProductNameUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.UpdateProductStockUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/branches") // Es recomendable versionar tu API
@RequiredArgsConstructor
public class BranchController {

    private final AddProductUseCase addProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    private final UpdateBranchNameUseCase updateBranchNameUseCase;
    private final UpdateProductNameUseCase updateProductNameUseCase;

    @PostMapping("/{id}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> addProductToBranch(
            @PathVariable("id") String branchId,
            @Valid @RequestBody CreateProductRequest request) { // @Valid añadido
        return addProductUseCase.execute(branchId, request);
    }

    @DeleteMapping("/{branchId}/products/{productId}") // Cambiado a productId por consistencia
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable String branchId,
            @PathVariable String productId) {
        return deleteProductUseCase.execute(branchId, productId);
    }

    @PatchMapping("/{branchId}/products/{productId}/stock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateStock(
            @PathVariable String branchId,
            @PathVariable String productId,
            @Valid @RequestBody UpdateStockRequest request) { // @Valid añadido
        return updateProductStockUseCase.execute(productId, request.getNewStock());
    }

    @PatchMapping("/{id}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateName(@PathVariable String id,
            @RequestBody UpdateNameRequest request) {
        return updateBranchNameUseCase.execute(id, request.getName());
    }

    @PatchMapping("/{branchId}/products/{productId}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateProductName(@PathVariable String branchId,
            @PathVariable String productId,
            @RequestBody UpdateNameRequest request) {
        return updateProductNameUseCase.execute(branchId, productId, request.getName());
    }
}