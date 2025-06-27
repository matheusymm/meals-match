package com.ufscar.pooa.backend.dto.Recipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientCreateDTO;

public record RecipeCreateDTO(
    @NotBlank String name,
    @NotNull UUID authorId,
    @NotBlank String preparationMethods,
    @NotNull List<RecipeIngredientCreateDTO> ingredients,
    @NotNull List<String> categories
) {
}
