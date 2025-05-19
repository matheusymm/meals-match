package com.ufscar.pooa.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.RecipeDTO;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.repository.RecipeRepository;

@Service
public class RecipeService implements IRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
   public RecipeDTO createRecipe(RecipeDTO recipeDTO){
        Recipe recipe = new Recipe();

        recipe.setName(recipeDTO.name());
        recipe.setPreparationMethods(recipeDTO.preparationMethods());
        recipe.setIngredients(recipeDTO.ingredients());
        recipe.setCategories(recipeDTO.categories());
        recipe.setComments(recipeDTO.comments());

        recipeRepository.save(recipe);
        return new RecipeDTO(recipe.getName(), recipe.getPreparationMethods(), recipe.getIngredients(), recipe.getCategories(), recipe.getComments());
   }

   @Override
    public RecipeDTO updateRecipe(UUID recipeId, RecipeDTO recipeDTO){
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (recipe == null) {
            throw new RuntimeException("Recipe not found");
        }

        recipe.setName(recipeDTO.name());
        recipe.setPreparationMethods(recipeDTO.preparationMethods());
        recipe.setIngredients(recipeDTO.ingredients());
        recipe.setCategories(recipeDTO.categories());
        recipe.setComments(recipeDTO.comments());
        recipeRepository.save(recipe);

        return new RecipeDTO(recipe.getName(), recipe.getPreparationMethods(), recipe.getIngredients(), recipe.getCategories(), recipe.getComments());
    }

    @Override
    public void deleteRecipe(UUID recipeId){
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (recipe == null) {
            throw new RuntimeException("User not found");
        }

        recipeRepository.deleteById(recipeId);
    }

    @Override
    public RecipeDTO getRecipeByName(String name){
         Recipe recipe = recipeRepository.findByName(name);

        if (recipe == null) {
            throw new RuntimeException("Recipe not found");
        }

        return new RecipeDTO(recipe.getName(), recipe.getPreparationMethods(), recipe.getIngredients(), recipe.getCategories(), recipe.getComments());
    }

    @Override
    public List<RecipeDTO> getRecipesByCategory(String category){
         List<Recipe> recipes = recipeRepository.findByCategoriesContaining(category);

        if (recipes.isEmpty()) {
            throw new RuntimeException("No recipes found");
        }

        return new ArrayList<>(recipes.stream()
                .map(recipe -> new RecipeDTO(recipe.getName(), recipe.getPreparationMethods(), recipe.getIngredients(), recipe.getCategories(), recipe.getComments()))
                .toList());
    }


    @Override
    public List<RecipeDTO> getRecipesByIngredients(List<String> ingredients){
        List<Recipe> recipes = recipeRepository.findByIngredientsIn(ingredients);

        if (recipes.isEmpty()) {
            throw new RuntimeException("No recipes found");
        }

        return new ArrayList<>(recipes.stream()
                .map(recipe -> new RecipeDTO(recipe.getName(), recipe.getPreparationMethods(), recipe.getIngredients(), recipe.getCategories(), recipe.getComments()))
                .toList());
    }

    @Override
    public List<RecipeDTO> getAllRecipes(){
         List<Recipe> recipes = recipeRepository.findAll();

        if (recipes.isEmpty()) {
            throw new RuntimeException("No recipes found");
        }

        return new ArrayList<>(recipes.stream()
                .map(recipe -> new RecipeDTO(recipe.getName(), recipe.getPreparationMethods(), recipe.getIngredients(), recipe.getCategories(), recipe.getComments()))
                .toList());
    }
}
