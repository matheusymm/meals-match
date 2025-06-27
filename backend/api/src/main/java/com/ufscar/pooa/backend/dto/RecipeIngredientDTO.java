package com.ufscar.pooa.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record RecipeIngredientDTO(
    UUID id,
    @NotNull UUID recipeId,
    @NotBlank String ingredientName,
    @NotNull Float quantity,
    @NotBlank String unit
) {
    public RecipeIngredientDTO(UUID id, UUID recipeId, String ingredientName, Float quantity, String unit) {
        this.id = id;
        this.recipeId = recipeId;   
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }
}
