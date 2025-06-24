package com.ufscar.pooa.backend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ufscar.pooa.backend.dto.IngredientDTO;
import com.ufscar.pooa.backend.model.Ingredient;
import com.ufscar.pooa.backend.repository.IngredientRepository;
import com.ufscar.pooa.backend.service.interfaces.IIngredientService;

@Service
public class IngredientService implements IIngredientService {

    @Autowired
    private IngredientRepository IngredientRepository;

    @Override
    public IngredientDTO createIngredient(IngredientDTO IngredientDTO) {

        Ingredient Ingredient = new Ingredient();

        Ingredient.setName(IngredientDTO.name());

        IngredientRepository.save(Ingredient);

        return new IngredientDTO(Ingredient.getId(), Ingredient.getName());
    }

    @Override
    public IngredientDTO updateIngredient(UUID IngredientId,
            IngredientDTO IngredientDTO) {

        Ingredient Ingredient = IngredientRepository.findById(IngredientId).orElse(null);

        if (Ingredient == null) {
            throw new RuntimeException(" not found");
        }
        Ingredient.setName(IngredientDTO.name());

        IngredientRepository.save(Ingredient);

        return new IngredientDTO(Ingredient.getId(), Ingredient.getName());
    }

    @Override
    public void deleteIngredient(UUID IngredientId) {
        Ingredient Ingredient = IngredientRepository.findById(IngredientId).orElse(null);

        if (Ingredient == null) {
            throw new RuntimeException(" not found");
        }
        IngredientRepository.deleteById(IngredientId);
    }

    @Override
    public IngredientDTO getIngredientById(UUID id) {
        Ingredient Ingredient = IngredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Ingredient not found"));

       return new IngredientDTO(Ingredient.getId(), Ingredient.getName());

    }

    @Override
    public IngredientDTO getIngredientByName(String name){
        Ingredient Ingredient = IngredientRepository.findByName(name);

        if (Ingredient == null) {
            throw new RuntimeException("Recipe not found");
        }

       return new IngredientDTO(Ingredient.getId(), Ingredient.getName());

    }

    @Override
    public List<IngredientDTO> getAllIngredients() {
        List<Ingredient> Ingredients = IngredientRepository.findAll();

        if (Ingredients.isEmpty()) {
            throw new RuntimeException("No  ingredients found");
        }

        return Ingredients.stream().map(Ingredient -> {

            return new IngredientDTO(Ingredient.getId(), Ingredient.getName());

        }).toList();
    }

}
