package com.ufscar.pooa.backend.dto.Ingredient;

import jakarta.validation.constraints.NotBlank;

public record IngredientCreateDTO(
    @NotBlank String name
) {
}
