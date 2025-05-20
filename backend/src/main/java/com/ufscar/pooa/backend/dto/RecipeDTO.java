package com.ufscar.pooa.backend.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecipeDTO (
    @NotBlank String name,
    @NotBlank String preparationMethods,
    @NotNull Float rating,
    @NotBlank List<String> ingredients,
    @NotBlank List<String> categories,
    List<String> comments) {
      public RecipeDTO(String name, String preparationMethods, Float rating, List<String> ingredients, List<String> categories, List<String> comments) {
        this.name = name;
        this.preparationMethods = preparationMethods;
        this.rating = rating;
        this.ingredients = ingredients;
        this.categories = categories;
        this.comments = comments;
    }
  }
