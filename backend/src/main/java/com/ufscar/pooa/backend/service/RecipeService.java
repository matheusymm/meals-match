package com.ufscar.pooa.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.RecipeDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredientsDTO;
import com.ufscar.pooa.backend.repository.RecipeRepository;

@Service
public class RecipeService implements IRecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public  void registerRecipe(String name, String preparationMethods, List<RecipeIngredientsDTO> ingredients, List<String> categories) {
        // Implement the logic to register a recipe
    }

    @Override
    public void updateRecipe(String recipeId, String name, String preparationMethods, List<RecipeIngredientsDTO> ingredients, List<String> categories) {
        // Implement the logic to update a recipe
    }

    @Override
    public void deleteRecipe(String recipeId) {
        // Implement the logic to delete a recipe
    }

    @Override
    public RecipeDTO getRecipeByName(String name) {
        // Implement the logic to get a recipe by recipename
        return null;
    }

    @Override
    public List<RecipeDTO> getRecipesByCategory(String category) {
        // Implement the logic to get the recipes that fit a specific category
        return null;
    }

    @Override
    public List<RecipeDTO> getRecipesByIngredients(List<RecipeIngredientsDTO> ingredients) {
        // Implement the logic to get the recipes that use some specific ingredients
        return null;
    }

    @Override
    public  List<RecipeDTO> getAllRecipes() {
        // Implement the logic to get all recipes
        return null;
    }
}
