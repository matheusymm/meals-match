package com.ufscar.pooa.backend.service;

import java.util.List;
import java.util.UUID;

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
   public RecipeDTO createRecipe(RecipeDTO recipeDTO){
        return null;
   }

   @Override
    public RecipeDTO updateRecipe(UUID recipeId, RecipeDTO recipeDTO){
        return null;
    }

    @Override
    public void deleteRecipe(UUID recipeId){
       ;
    }

    @Override
    public RecipeDTO getRecipeByName(String name){
        return null;
    }

    @Override
    public List<RecipeDTO> getRecipesByCategory(String category){
        return null;
    }

    @Override
    public List<RecipeDTO> getRecipesByIngredients(List<RecipeIngredientsDTO> ingredients){
        return null;
    }

    @Override
    public List<RecipeDTO> getAllRecipes(){
        return null;
    }
}
