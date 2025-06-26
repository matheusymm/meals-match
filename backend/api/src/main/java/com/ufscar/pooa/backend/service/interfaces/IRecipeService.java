package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.RecipeDTO;

public interface IRecipeService {

    RecipeDTO createRecipe(RecipeDTO recipeDTO);

    //RecipeDTO updateRecipe(UUID recipeId, RecipeDTO recipeDTO);

    void deleteRecipe(UUID recipeId);

    RecipeDTO getRecipeByName(String name);

    RecipeDTO getRecipeById(UUID id);

    // List<RecipeDTO> getRecipesByCategory(String category);

    // List<RecipeDTO> getRecipesByIngredients(List<Ingredient> ingredients);

    List<RecipeDTO> getRecipesByUserId(UUID authorId);

    List<RecipeDTO> getAllRecipes();
}
