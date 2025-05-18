package com.ufscar.pooa.backend.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;

public record RecipeDTO (
    @NotBlank String name,
    @NotBlank String preparationMethods,
    @NotBlank List<RecipeIngredientsDTO> ingredients,
    @NotBlank List<String> categories,
    @NotBlank List<CommentsDTO> comments) {
      public RecipeDTO(String name, String preparationMethods, List<RecipeIngredientsDTO> ingredients, List<String> categories, List<CommentsDTO> comments) {
        this.name = name;
        this.preparationMethods = preparationMethods;
        this.ingredients = ingredients;
        this.categories = categories;
        this.comments = comments;
    }
  }
