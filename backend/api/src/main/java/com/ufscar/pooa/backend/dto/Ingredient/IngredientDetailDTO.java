package com.ufscar.pooa.backend.dto.Ingredient;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record IngredientDetailDTO(
        UUID id,
        @NotBlank String name) {
    public IngredientDetailDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
