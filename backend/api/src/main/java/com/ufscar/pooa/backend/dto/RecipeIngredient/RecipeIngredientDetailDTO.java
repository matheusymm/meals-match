package com.ufscar.pooa.backend.dto.RecipeIngredient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record RecipeIngredientDetailDTO(
    UUID id,
    @NotNull UUID recipeId,
    @NotNull String ingredientId,
    @NotNull Float quantity,
    @NotBlank String unit
) {
    public RecipeIngredientDetailDTO(UUID id, UUID recipeId, String ingredientId, Float quantity, String unit) {
        this.id = id;
        this.recipeId = recipeId;   
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.unit = unit;
    }
}
