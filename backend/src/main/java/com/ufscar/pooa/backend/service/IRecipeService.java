package com.ufscar.pooa.backend.service;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.RecipeDTO;
import com.ufscar.pooa.backend.model.Recipe;

public interface IRecipeService {
    
    RecipeDTO createRecipe(RecipeDTO recipeDTO);

    RecipeDTO updateRecipe(UUID recipeId, RecipeDTO recipeDTO);

    void deleteRecipe(UUID recipeId);

    RecipeDTO getRecipeByName(String name);

    RecipeDTO getRecipeById(UUID id);

    List<RecipeDTO> getRecipesByCategory(String category);

    List<RecipeDTO> getRecipesByIngredients(List<String> ingredients);

    List<RecipeDTO> getAllRecipes();
}
