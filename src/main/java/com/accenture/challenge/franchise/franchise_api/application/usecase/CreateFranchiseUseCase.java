package com.accenture.challenge.franchise.franchise_api.application.usecase;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.application.dto.CreateFranchiseRequest;
import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;
import com.accenture.challenge.franchise.franchise_api.domain.repository.FranchiseRepository;

import reactor.core.publisher.Mono;

@Service
public class CreateFranchiseUseCase {

    private final FranchiseRepository franchiseRepository;

    public CreateFranchiseUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Mono<Void> execute(CreateFranchiseRequest request) {
        // 1. Convertimos el DTO en nuestro modelo puro de Dominio
        // Pasamos 'null' en el ID para que MongoDB genere uno automáticamente
        Franchise newFranchise = new Franchise(null, request.getName(), new ArrayList<>());
        
        // 2. Lo guardamos en la base de datos y retornamos un Mono<Void>
        return franchiseRepository.save(newFranchise).then();
    }
}