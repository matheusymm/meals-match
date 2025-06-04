package com.ufscar.pooa.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.RecipeDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredientDTO;
import com.ufscar.pooa.backend.model.RecipeIngredient;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.repository.RecipeIngredientRepository;
import com.ufscar.pooa.backend.repository.RecipeRepository;
import com.ufscar.pooa.backend.service.interfaces.IRecipeIngredientService;

@Service
public class RecipeIngredientService implements IRecipeIngredientService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public RecipeIngredientDTO createRecipeIngredient(RecipeIngredientDTO recipeIngredientDTO) {

        Recipe recipe = recipeRepository.findById(recipeIngredientDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RecipeIngredient recipeIngredient = new RecipeIngredient();

        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(recipeIngredientDTO.ingredientId());
        recipeIngredient.setQuantity(recipeIngredientDTO.quantity());
        recipeIngredient.setUnit(recipeIngredientDTO.unit());

        recipeIngredientRepository.save(recipeIngredient);

        return new RecipeIngredientDTO(recipeIngredient.getId(), recipeIngredient.getRecipe().getId(),
                recipeIngredient.getIngredient(), recipeIngredient.getQuantity(), recipeIngredient.getUnit());
    }

    @Override
    public RecipeIngredientDTO updateRecipeIngredient(UUID recipeIngredientId,
            RecipeIngredientDTO recipeIngredientDTO) {

        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(recipeIngredientId).orElse(null);

        if (recipeIngredient == null) {
            throw new RuntimeException("Recipe not found");
        }

        Recipe recipe = recipeRepository.findById(recipeIngredientDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(recipeIngredientDTO.ingredientId());
        recipeIngredient.setQuantity(recipeIngredientDTO.quantity());
        recipeIngredient.setUnit(recipeIngredientDTO.unit());

        recipeIngredientRepository.save(recipeIngredient);

        return new RecipeIngredientDTO(recipeIngredient.getId(), recipeIngredient.getRecipe().getId(),
                recipeIngredient.getIngredient(), recipeIngredient.getQuantity(), recipeIngredient.getUnit());

    }

    @Override
    public void deleteRecipeIngredient(UUID recipeIngredientId) {
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(recipeIngredientId).orElse(null);

        if (recipeIngredient == null) {
            throw new RuntimeException("Recipe not found");
        }
        recipeIngredientRepository.deleteById(recipeIngredientId);
    }

    @Override
    public RecipeIngredientDTO getRecipeIngredientById(UUID id) {
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe Ingredient not found"));

        return new RecipeIngredientDTO(recipeIngredient.getId(), recipeIngredient.getRecipe().getId(),
                recipeIngredient.getIngredient(), recipeIngredient.getQuantity(), recipeIngredient.getUnit());

    }

    @Override
    public List<RecipeIngredientDTO> getAllRecipeIngredients() {
        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findAll();

        if (recipeIngredients.isEmpty()) {
            throw new RuntimeException("No recipe ingredients found");
        }

        return recipeIngredients.stream().map(recipeIngredient -> {

            return new RecipeIngredientDTO(recipeIngredient.getId(), recipeIngredient.getRecipe().getId(),
                    recipeIngredient.getIngredient(), recipeIngredient.getQuantity(), recipeIngredient.getUnit());

        }).toList();
    }

}
