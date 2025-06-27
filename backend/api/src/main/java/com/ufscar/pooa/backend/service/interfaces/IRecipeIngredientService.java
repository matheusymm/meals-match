package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientCreateDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientDetailDTO;

public interface IRecipeIngredientService {

    RecipeIngredientDetailDTO createRecipeIngredient(RecipeIngredientCreateDTO recipeIngredientCreateDTO);

    RecipeIngredientDetailDTO updateRecipeIngredient(UUID recipeIngredientId, RecipeIngredientCreateDTO recipeIngredientCreateDTO);

    void deleteRecipeIngredient(UUID recipeIngredientId);

    RecipeIngredientDetailDTO getRecipeIngredientById(UUID id);

    List<RecipeIngredientDetailDTO> getAllRecipeIngredients();
}
