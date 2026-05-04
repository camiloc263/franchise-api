package com.accenture.challenge.franchise.franchise_api.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor; // Faltaba esta importación
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; // Faltaba esta importación

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Franchise {

    private String id; // Corregido: antes estaba incompleto
    private String name;
    
    @Builder.Default
    private List<Branch> branches = new ArrayList<>();

    
    public void updateName(String newName) {
        validateName(newName);
        this.name = newName;
    }

    public void addBranch(Branch branch) {
        if (branch == null) {
            throw new IllegalArgumentException("La sucursal no puede ser nula");
        }
        // Aseguramos que la lista esté inicializada
        if (this.branches == null) {
            this.branches = new ArrayList<>();
        }
        this.branches.add(branch);
    }

    public void removeBranch(String branchId) {
        if (this.branches != null) {
            this.branches.removeIf(b -> b.getId().equals(branchId));
        }
    }

    // Sobrescribimos el getter de Lombok para proteger la integridad de la colección
    public List<Branch> getBranches() {
        return branches != null ? Collections.unmodifiableList(branches) : Collections.emptyList();
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la franquicia no puede estar vacío");
        }
    }
}