package com.ufscar.pooa.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.model.RecipeIngredient;
import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientCreateDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientDetailDTO;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.repository.RecipeIngredientRepository;
import com.ufscar.pooa.backend.repository.RecipeRepository;
import com.ufscar.pooa.backend.service.interfaces.IRecipeIngredientService;
import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientDTOFactory;

@Service
public class RecipeIngredientService implements IRecipeIngredientService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public RecipeIngredientDetailDTO createRecipeIngredient(RecipeIngredientCreateDTO recipeIngredientCreateDTO) {
        Recipe recipe = recipeRepository.findById(recipeIngredientCreateDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(recipeIngredientCreateDTO.ingredientId());
        recipeIngredient.setQuantity(recipeIngredientCreateDTO.quantity());
        recipeIngredient.setUnit(recipeIngredientCreateDTO.unit());

        RecipeIngredient savedRecipeIngredient = recipeIngredientRepository.save(recipeIngredient);

        return RecipeIngredientDTOFactory.toDetailDTO(savedRecipeIngredient);
    }

    @Override
    public RecipeIngredientDetailDTO updateRecipeIngredient(UUID recipeIngredientId, RecipeIngredientCreateDTO recipeIngredientCreateDTO) {
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(recipeIngredientId)
                .orElseThrow(() -> new RuntimeException("Recipe Ingredient not found"));

        Recipe recipe = recipeRepository.findById(recipeIngredientCreateDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(recipeIngredientCreateDTO.ingredientId());
        recipeIngredient.setQuantity(recipeIngredientCreateDTO.quantity());
        recipeIngredient.setUnit(recipeIngredientCreateDTO.unit());

        RecipeIngredient savedRecipeIngredient = recipeIngredientRepository.save(recipeIngredient);

        return RecipeIngredientDTOFactory.toDetailDTO(savedRecipeIngredient);
    }

    @Override
    public void deleteRecipeIngredient(UUID recipeIngredientId) {
        recipeIngredientRepository.findById(recipeIngredientId)
                .orElseThrow(() -> new RuntimeException("Recipe Ingredient not found"));
        recipeIngredientRepository.deleteById(recipeIngredientId);
    }

    @Override
    public RecipeIngredientDetailDTO getRecipeIngredientById(UUID id) {
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe Ingredient not found"));
        return RecipeIngredientDTOFactory.toDetailDTO(recipeIngredient);
    }

    @Override
    public List<RecipeIngredientDetailDTO> getAllRecipeIngredients() {
        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findAll();
        if (recipeIngredients.isEmpty()) {
            throw new RuntimeException("No recipe ingredients found");
        }
        return recipeIngredients.stream().map(RecipeIngredientDTOFactory::toDetailDTO).toList();
    }
}
