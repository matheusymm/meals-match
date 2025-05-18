package com.ufscar.pooa.backend.dto;

import java.util.List;

import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.RecipeIngredients;

import jakarta.validation.constraints.NotBlank;

public record RecipeDTO (
    @NotBlank String name,
    @NotBlank String preparationMethods,
    @NotBlank List<RecipeIngredientsDTO> ingredients,
    @NotBlank List<String> categories,
    @NotBlank List<CommentDTO> comments) {
      public RecipeDTO(String name, String preparationMethods, List<RecipeIngredientsDTO> ingredients, List<String> categories, List<CommentDTO> comments) {
        this.name = name;
        this.preparationMethods = preparationMethods;
        this.ingredients = ingredients;
        this.categories = categories;
        this.comments = comments;
    }

    public List<RecipeIngredients> getRecipeIngredients() {
        return ingredients.stream()
            .map(RecipeIngredientsDTO::toEntity)
            .toList();
    }  
    public List<Comment> getComments() {
        return comments.stream()
            .map(CommentDTO::toEntity)
            .toList();
    }
  }
