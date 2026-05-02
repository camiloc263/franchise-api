package com.accenture.challenge.franchise.franchise_api.infrastructure.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.FranchiseDocument;

@Component
public class FranchiseMapper {

    // Dominio -> Infraestructura (Para guardar en MongoDB)
    public FranchiseDocument toDocument(Franchise domain) {
        if (domain == null) return null;
        
        return FranchiseDocument.builder()
                .id(domain.getId())
                .name(domain.getName())
                // Ya no mapeamos "branches" porque no existen en el documento de Mongo
                .build();
    }

    // Infraestructura -> Dominio (Para leer de MongoDB)
    public Franchise toDomain(FranchiseDocument doc) {
        if (doc == null) return null;

        // Utilizamos el constructor rico de tu modelo de Dominio
        return new Franchise(
                doc.getId(),
                doc.getName(),
                new ArrayList<>() // Inicializamos la lista vacía por defecto
        );
    }

}