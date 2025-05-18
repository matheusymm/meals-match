package com.ufscar.pooa.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.dto.RecipeIngredientsDTO;
import com.ufscar.pooa.backend.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {

    void createRecipe(String name, String preparationMethods, List<RecipeIngredientsDTO> ingredients, List<String> categories);

    void updateRecipe(UUID recipeId, String name, String preparationMethods, List<RecipeIngredientsDTO> ingredients, List<String> categories);

    void deleteRecipe(UUID recipeId);

    Optional<Recipe> findById(UUID id);

    Recipe findByRecipename(String name);

    List<Recipe> findByCategory(String category);

    List<Recipe> findByIngredients(List<RecipeIngredientsDTO> ingredients);

    List<Recipe> findAll();
}