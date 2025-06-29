package com.ufscar.pooa.backend.dto.Recipe;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.ufscar.pooa.backend.dto.Category.CategoryDetailDTO;
import com.ufscar.pooa.backend.dto.Comment.CommentDetailDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientDetailDTO;

public record RecipeDetailDTO(
        UUID id,
        @NotBlank String name,
        @NotNull UUID authorId,
        @NotBlank String preparationMethods,
        Double rating,
        @NotNull List<RecipeIngredientDetailDTO> ingredients,
        List<CategoryDetailDTO> categories,
        List<CommentDetailDTO> comments) {
    public RecipeDetailDTO(UUID id, String name, UUID authorId, String preparationMethods, Double rating,
            List<RecipeIngredientDetailDTO> ingredients,
            List<CategoryDetailDTO> categories, List<CommentDetailDTO> comments) {
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