package com.ufscar.pooa.backend.service;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.RecipeDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredientsDTO;

public interface IRecipeService {
    RecipeDTO createRecipe(RecipeDTO recipeDTO);

    RecipeDTO updateRecipe(UUID recipeId, RecipeDTO recipeDTO);

    void deleteRecipe(UUID recipeId);

    RecipeDTO getRecipeByName(String name);

    List<RecipeDTO> getRecipesByCategory(String category);

    List<RecipeDTO> getRecipesByIngredients(List<RecipeIngredientsDTO> ingredients);

    List<RecipeDTO> getAllRecipes();
}
