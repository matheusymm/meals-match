package com.ufscar.pooa.backend.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.Ingredient;

public record RecipeDTO(
        UUID id,
        @NotBlank String name,
        @NotNull UUID authorId,
        @NotBlank String preparationMethods,
        Double rating,
        @NotNull List<Ingredient> ingredients,
        // @NotBlank List<String> categories,
        List<Comment> comments) {
    public RecipeDTO(UUID id, String name, UUID authorId, String preparationMethods, Double rating, List<Ingredient> ingredients,
            List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.preparationMethods = preparationMethods;
        this.rating = rating;
        this.ingredients = ingredients;
        // this.categories = categories;
        this.comments = comments;
    }
}