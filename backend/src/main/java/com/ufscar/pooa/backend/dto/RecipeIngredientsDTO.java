package com.ufscar.pooa.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

import com.ufscar.pooa.backend.model.RecipeIngredients;

public record RecipeIngredientsDTO(
    UUID id,
    
    @NotNull
    IngredientDTO ingredient,
    
    @NotNull
    @Positive
    float quantity,
    
    @NotBlank
    String unit

) {
    public static RecipeIngredientsDTO fromEntity(RecipeIngredients entity) {
        return new RecipeIngredientsDTO(
            entity.getId(),
            IngredientDTO.fromEntity(entity.getIngredient()),
            entity.getQuantity(),
            entity.getUnit()
        );
    }
    public RecipeIngredients toEntity() {
        return new RecipeIngredients(
            this.id,
            this.ingredient.toEntity(),
            this.quantity,
            this.unit
        );
    }
}
