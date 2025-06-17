package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.IngredientDTO;

public interface IIngredientService {

    IngredientDTO createIngredient(IngredientDTO IngredientDTO);

    IngredientDTO updateIngredient(UUID IngredientId, IngredientDTO IngredientDTO);

    void deleteIngredient(UUID IngredientId);

    IngredientDTO getIngredientById(UUID id);

    IngredientDTO getIngredientByName(String name);

    List<IngredientDTO> getAllIngredients();
}
