package com.ufscar.pooa.backend.dto.Recipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record RecipeCreateDTO(
    @NotBlank String name,
    @NotNull UUID authorId,
    @NotBlank String preparationMethods,
    @NotNull List<String> ingredientIds,
    List<String> categoryIds
) {
}
