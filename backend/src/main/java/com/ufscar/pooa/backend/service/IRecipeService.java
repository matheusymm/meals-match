package com.ufscar.pooa.backend.service;

import java.util.List;

import com.ufscar.pooa.backend.dto.RecipeDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredientsDTO;

public interface IRecipeService {
    void registerRecipe(String name, String preparationMethods, List<RecipeIngredientsDTO> ingredients, List<String> categories);

    void updateRecipe(String recipeId, String name, String preparationMethods, List<RecipeIngredientsDTO> ingredients, List<String> categories);

    void deleteRecipe(String recipeId);

    RecipeDTO getRecipeByName(String name);

    List<RecipeDTO> getRecipesByCategory(String category);

    List<RecipeDTO> getRecipesByIngredients(List<RecipeIngredientsDTO> ingredients);

    List<RecipeDTO> getAllRecipes();
}
