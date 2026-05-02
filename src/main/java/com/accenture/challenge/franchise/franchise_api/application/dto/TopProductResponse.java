package com.accenture.challenge.franchise.franchise_api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopProductResponse {
    private String branchName;
    private String productName;
    private int stock;
}