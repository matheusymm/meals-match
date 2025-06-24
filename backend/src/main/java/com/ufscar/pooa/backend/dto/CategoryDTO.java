package com.ufscar.pooa.backend.dto;

import java.util.UUID;

import com.ufscar.pooa.backend.model.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
    UUID id,
    @NotBlank String name
) {
    public static CategoryDTO fromCategory(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
