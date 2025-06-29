package com.ufscar.pooa.backend.dto.RecipeIngredient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record RecipeIngredientDetailDTO(
        UUID id,
        @NotBlank String ingredientName,
        @NotNull Float quantity,
        @NotBlank String unit) {
    public RecipeIngredientDetailDTO(UUID id, String ingredientName, Float quantity, String unit) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }
}
