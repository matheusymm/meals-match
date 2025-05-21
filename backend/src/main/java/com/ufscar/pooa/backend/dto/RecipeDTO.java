package com.ufscar.pooa.backend.dto;

import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecipeDTO(
        UUID id,
        @NotBlank String name,
        @NotNull UUID authorId,
        @NotBlank String preparationMethods,
        @NotNull Double rating,
        @NotBlank List<String> ingredients,
        @NotBlank List<String> categories,
        List<String> comments) {
    public RecipeDTO(UUID id, String name, UUID authorId, String preparationMethods, Double rating, List<String> ingredients,
            List<String> categories, List<String> comments) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.preparationMethods = preparationMethods;
        this.rating = rating;
        this.ingredients = ingredients;
        this.categories = categories;
        this.comments = comments;
    }
}