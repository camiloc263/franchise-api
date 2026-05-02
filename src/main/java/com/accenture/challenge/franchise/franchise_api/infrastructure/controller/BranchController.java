package com.accenture.challenge.franchise.franchise_api.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateProductRequest;
import com.accenture.challenge.franchise.franchise_api.application.dto.UpdateNameRequest;
import com.accenture.challenge.franchise.franchise_api.application.dto.UpdateStockRequest;
import com.accenture.challenge.franchise.franchise_api.application.usecase.AddProductUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.UpdateBranchNameUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.UpdateProductNameUseCase;
import com.accenture.challenge.franchise.franchise_api.application.usecase.UpdateProductStockUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Tag(name = "Branches", description = "Gestión de sucursales e inventarios de productos")
@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final AddProductUseCase addProductUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    private final UpdateBranchNameUseCase updateBranchNameUseCase;
    private final UpdateProductNameUseCase updateProductNameUseCase;

    @Operation(summary = "Agregar producto a sucursal", description = "Vincula un nuevo producto con su stock inicial a una sucursal específica mediante su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos del producto"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PostMapping("/{id}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> addProductToBranch(
            @Parameter(description = "ID único de la sucursal", example = "65a2b...") @PathVariable("id") String branchId,
            @Valid @RequestBody CreateProductRequest request) {
        return addProductUseCase.execute(branchId, request);
    }

    @Operation(summary = "Actualizar stock de un producto", description = "Modifica la cantidad disponible de un producto específico. Se valida que el nuevo stock no sea negativo.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Stock actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de stock inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto o sucursal no hallados")
    })
    @PatchMapping("/{branchId}/products/{productId}/stock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateStock(
            @Parameter(description = "ID de la sucursal") @PathVariable String branchId,
            @Parameter(description = "ID del producto a modificar") @PathVariable String productId,
            @Valid @RequestBody UpdateStockRequest request) {
        return updateProductStockUseCase.execute(productId, request.getNewStock());
    }

    @Operation(summary = "Actualizar nombre de la sucursal", description = "Permite renombrar una sucursal existente en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Nombre actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "ID de sucursal inexistente")
    })
    @PatchMapping("/{id}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateName(
            @Parameter(description = "ID de la sucursal") @PathVariable String id,
            @Valid @RequestBody UpdateNameRequest request) {
        return updateBranchNameUseCase.execute(id, request.getName());
    }

    @Operation(summary = "Actualizar nombre de un producto", description = "Modifica el nombre comercial de un producto dentro del inventario de una sucursal.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Nombre del producto actualizado"),
            @ApiResponse(responseCode = "400", description = "El nuevo nombre no cumple con los requisitos"),
            @ApiResponse(responseCode = "404", description = "Referencia de producto no encontrada")
    })
    @PatchMapping("/{branchId}/products/{productId}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateProductName(
            @Parameter(description = "ID de la sucursal") @PathVariable String branchId,
            @Parameter(description = "ID del producto") @PathVariable String productId,
            @Valid @RequestBody UpdateNameRequest request) {
        return updateProductNameUseCase.execute(productId, request.getName());
    }
}