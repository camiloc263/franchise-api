package com.accenture.challenge.franchise.franchise_api.infrastructure.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.accenture.challenge.franchise.franchise_api.domain.model.Franchise;
import com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity.FranchiseDocument;

@Component
public class FranchiseMapper {

    public FranchiseDocument toDocument(Franchise domain) {
        if (domain == null) return null;
        
        return FranchiseDocument.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }

    public Franchise toDomain(FranchiseDocument doc) {
        if (doc == null) return null;

        return new Franchise(
                doc.getId(),
                doc.getName(),
                new ArrayList<>() 
        );
    }

}