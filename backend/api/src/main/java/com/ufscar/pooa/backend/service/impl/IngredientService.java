package com.ufscar.pooa.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.Ingredient.IngredientCreateDTO;
import com.ufscar.pooa.backend.dto.Ingredient.IngredientDTOFactory;
import com.ufscar.pooa.backend.dto.Ingredient.IngredientDetailDTO;
import com.ufscar.pooa.backend.model.Ingredient;
import com.ufscar.pooa.backend.repository.IngredientRepository;
import com.ufscar.pooa.backend.service.interfaces.IIngredientService;

@Service
public class IngredientService implements IIngredientService {

    @Autowired
    private IngredientRepository IngredientRepository;

    @Override
    public IngredientDetailDTO createIngredient(IngredientCreateDTO ingredientCreateDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientCreateDTO.name());
        IngredientRepository.save(ingredient);
        return IngredientDTOFactory.toDetailDTO(ingredient);
    }

    @Override
    public IngredientDetailDTO updateIngredient(UUID ingredientId, IngredientCreateDTO ingredientCreateDTO) {
        Ingredient ingredient = IngredientRepository.findById(ingredientId)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        ingredient.setName(ingredientCreateDTO.name());
        IngredientRepository.save(ingredient);
        return IngredientDTOFactory.toDetailDTO(ingredient);
    }

    @Override
    public void deleteIngredient(UUID ingredientId) {
        IngredientRepository.findById(ingredientId)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        IngredientRepository.deleteById(ingredientId);
    }

    @Override
    public IngredientDetailDTO getIngredientById(UUID id) {
        Ingredient ingredient = IngredientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        return IngredientDTOFactory.toDetailDTO(ingredient);
    }

    @Override
    public IngredientDetailDTO getIngredientByName(String name) {
        Ingredient ingredient = IngredientRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Ingredient not found: " + name));
        if (ingredient == null) {
            throw new RuntimeException("Ingredient not found");
        }

       return IngredientDTOFactory.toDetailDTO(ingredient);

    }

    @Override
    public List<IngredientDetailDTO> getAllIngredients() {
        List<Ingredient> ingredients = IngredientRepository.findAll();
        if (ingredients.isEmpty()) {
            throw new RuntimeException("No ingredients found");
        }

        return ingredients.stream()
            .map(IngredientDTOFactory::toDetailDTO)
            .toList();
    }
}
