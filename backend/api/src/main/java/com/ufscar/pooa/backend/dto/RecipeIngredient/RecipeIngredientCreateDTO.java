package com.ufscar.pooa.backend.dto.RecipeIngredient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record RecipeIngredientCreateDTO(
    @NotNull UUID recipeId,
    @NotNull String ingredientId,
    @NotNull Float quantity,
    @NotBlank String unit
) {
}
