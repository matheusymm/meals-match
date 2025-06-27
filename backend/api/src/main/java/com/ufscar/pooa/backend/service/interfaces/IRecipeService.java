package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.Recipe.RecipeCreateDTO;
import com.ufscar.pooa.backend.dto.Recipe.RecipeDetailDTO;

public interface IRecipeService {

    RecipeDetailDTO createRecipe(RecipeCreateDTO recipeCreateDTO);

    RecipeDetailDTO updateRecipe(UUID recipeId, RecipeCreateDTO recipeCreateDTO);

    void deleteRecipe(UUID recipeId);

    RecipeDetailDTO getRecipeByName(String name);

    RecipeDetailDTO getRecipeById(UUID id);

    // List<RecipeDTO> getRecipesByCategory(String category);

    // List<RecipeDTO> getRecipesByIngredients(List<Ingredient> ingredients);

    List<RecipeDetailDTO> getRecipesByUserId(UUID authorId);

    List<RecipeDetailDTO> getAllRecipes();
}
