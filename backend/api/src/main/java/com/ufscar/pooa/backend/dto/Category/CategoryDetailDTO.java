package com.ufscar.pooa.backend.dto.Category;

import java.util.UUID;

import com.ufscar.pooa.backend.model.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDetailDTO(
    UUID id,
    @NotBlank String name
) {
    public static CategoryDetailDTO fromCategory(Category category) {
        return new CategoryDetailDTO(category.getId(), category.getName());
    }
}
