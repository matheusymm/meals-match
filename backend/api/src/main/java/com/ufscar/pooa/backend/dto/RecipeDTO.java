package com.ufscar.pooa.backend.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.RecipeIngredient;
import com.ufscar.pooa.backend.model.Category;

public record RecipeDTO(
        UUID id,
        @NotBlank String name,
        @NotNull UUID authorId,
        @NotBlank String preparationMethods,
        Double rating,
        @NotNull List<RecipeIngredientDTO> ingredients,
        List<Category> categories,
        List<Comment> comments) {
    public RecipeDTO(UUID id, String name, UUID authorId, String preparationMethods, Double rating,
            List<RecipeIngredientDTO> ingredients,
            List<Category> categories, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.preparationMethods = preparationMethods;
        this.rating = rating;
        this.ingredients = ingredients;
        this.categories = categories;
        this.comments = comments;
    }

    public static RecipeDTO fromRecipe(Recipe recipe) {
        List<RecipeIngredientDTO> ingredientDTOs = recipe.getIngredients()
            .stream()
            .map(recipeIngredient -> new RecipeIngredientDTO(
                    recipeIngredient.getId(),
                    recipeIngredient.getRecipe().getId(),
                    recipeIngredient.getIngredient().getName(), 
                    recipeIngredient.getQuantity(),
                    recipeIngredient.getUnit()
            ))
            .toList();
        return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getAuthor().getId(),
                recipe.getPreparationMethods(), recipe.getRating(), ingredientDTOs, recipe.getCategories(),
                recipe.getComments());
    }
}