package com.accenture.challenge.franchise.franchise_api.application.usecase;

import org.springframework.stereotype.Service;

import com.accenture.challenge.franchise.franchise_api.application.dto.TopProductResponse;
import com.accenture.challenge.franchise.franchise_api.domain.repository.BranchRepository;
import com.accenture.challenge.franchise.franchise_api.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetTopProductsUseCase {

        private final BranchRepository branchRepository;
        private final ProductRepository productRepository;

        public Flux<TopProductResponse> execute(String franchiseId) {
                return branchRepository.findByFranchiseId(franchiseId)
                                .flatMap(branch -> productRepository.findTopByBranchId(branch.getId())
                                                .map(product -> new TopProductResponse(
                                                                branch.getName(),
                                                                product.getName(),
                                                                product.getStock()))

                                                .defaultIfEmpty(new TopProductResponse(branch.getName(),
                                                                "Sin productos", 0)));
        }
}