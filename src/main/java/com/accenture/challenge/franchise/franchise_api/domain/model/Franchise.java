package com.accenture.challenge.franchise.franchise_api.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Franchise {

    private String id;
    private String name;
    private List<Branch> branches;

    public Franchise(String id, String name, List<Branch> branches) {
        validateName(name);
        this.id = id;
        this.name = name;
        // Inicializamos la lista de forma segura
        this.branches = branches != null ? new ArrayList<>(branches) : new ArrayList<>();
    }

    public void updateName(String newName) {
        validateName(newName);
        this.name = newName;
    }

    public void addBranch(Branch branch) {
        if (branch == null) {
            throw new IllegalArgumentException("La sucursal no puede ser nula");
        }
        this.branches.add(branch);
    }

    public void removeBranch(String branchId) {
        this.branches.removeIf(b -> b.getId().equals(branchId));
    }

    // Protegemos la colección devolviendo una vista inmodificable
    public List<Branch> getBranches() {
        return Collections.unmodifiableList(branches);
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la franquicia no puede estar vacío");
        }
    }
}
