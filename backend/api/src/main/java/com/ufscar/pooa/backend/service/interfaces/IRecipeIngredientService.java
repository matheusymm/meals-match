package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.RecipeIngredientDTO;

public interface IRecipeIngredientService {

    RecipeIngredientDTO createRecipeIngredient(RecipeIngredientDTO recipeIngredientDTO);

    RecipeIngredientDTO updateRecipeIngredient(UUID recipeIngredientId, RecipeIngredientDTO recipeIngredientDTO);

    void deleteRecipeIngredient(UUID recipeIngredientId);

    RecipeIngredientDTO getRecipeIngredientById(UUID id);

    List<RecipeIngredientDTO> getAllRecipeIngredients();
}
