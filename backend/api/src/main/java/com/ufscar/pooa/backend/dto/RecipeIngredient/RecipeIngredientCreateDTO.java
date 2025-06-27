package com.ufscar.pooa.backend.dto.RecipeIngredient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record RecipeIngredientCreateDTO(
    @NotNull String ingredientName,
    @NotNull Float quantity,
    @NotBlank String unit
) {
}
