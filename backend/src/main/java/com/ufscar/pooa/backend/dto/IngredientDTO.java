package com.ufscar.pooa.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record IngredientDTO(
    UUID id,
    @NotBlank String name
) {
    public IngredientDTO(UUID id, String name) {
        this.id = id;
        this.name = name;   
    }
}
