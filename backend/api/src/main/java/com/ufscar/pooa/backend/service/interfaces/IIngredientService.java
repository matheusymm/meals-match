package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.Ingredient.IngredientCreateDTO;
import com.ufscar.pooa.backend.dto.Ingredient.IngredientDetailDTO;

public interface IIngredientService {

    IngredientDetailDTO createIngredient(IngredientCreateDTO ingredientCreateDTO);

    IngredientDetailDTO updateIngredient(UUID ingredientId, IngredientCreateDTO ingredientCreateDTO);

    void deleteIngredient(UUID ingredientId);

    IngredientDetailDTO getIngredientById(UUID id);

    IngredientDetailDTO getIngredientByName(String name);

    List<IngredientDetailDTO> getAllIngredients();
}
