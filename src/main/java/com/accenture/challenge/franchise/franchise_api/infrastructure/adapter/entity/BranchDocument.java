package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "branches") 
public class BranchDocument {

    @Id
    private String id;
    private String name;
    private String franchiseId; 
    
    
    private List<ProductDocument> products; 
}