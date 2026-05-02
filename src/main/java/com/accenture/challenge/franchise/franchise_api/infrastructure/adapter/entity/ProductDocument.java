package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDocument {

  
    private String id; 
    private String name;
    private Integer stock;
    private String branchId;
}