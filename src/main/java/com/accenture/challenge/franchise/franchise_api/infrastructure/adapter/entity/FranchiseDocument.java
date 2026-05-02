package com.accenture.challenge.franchise.franchise_api.infrastructure.adapter.entity;

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
@Document(collection = "franchises")
public class FranchiseDocument {

    @Id
    private String id;
    private String name;
}