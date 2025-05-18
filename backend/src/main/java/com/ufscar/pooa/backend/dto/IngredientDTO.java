package com.ufscar.pooa.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

import com.ufscar.pooa.backend.model.Ingredient;

public record IngredientDTO(
    UUID id,
    @NotBlank String name
) {
    public static IngredientDTO fromEntity(Ingredient entity) {
        return new IngredientDTO(
            entity.getId(),
            entity.getName()
        );
    }
    public Ingredient toEntity() {
        return new Ingredient(
            this.id,
            this.name
        );
    }
}