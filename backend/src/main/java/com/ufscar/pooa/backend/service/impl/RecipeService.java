package com.ufscar.pooa.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.RecipeDTO;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.repository.RatingRepository;
import com.ufscar.pooa.backend.repository.RecipeRepository;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.service.interfaces.IRecipeService;

@Service
public class RecipeService implements IRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {

        User author = userRepository.findById(recipeDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recipe recipe = new Recipe();

        recipe.setName(recipeDTO.name());
        recipe.setAuthor(author);
        recipe.setPreparationMethods(recipeDTO.preparationMethods());
        recipe.setRating(0.0);
        // recipe.setIngredients(recipeDTO.ingredients());
        // recipe.setCategories(recipeDTO.categories());
        recipe.setComments(recipeDTO.comments());
        recipe.setCreatedAt(new Date());

        recipeRepository.save(recipe);

        return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getAuthor().getId(),
                recipe.getPreparationMethods(), recipe.getRating(), recipe.getComments());
    }

    @Override
    public RecipeDTO updateRecipe(UUID recipeId, RecipeDTO recipeDTO) {

        User author = userRepository.findById(recipeDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (recipe == null) {
            throw new RuntimeException("Recipe not found");
        }

        recipe.setName(recipeDTO.name());
        recipe.setAuthor(author);
        recipe.setPreparationMethods(recipeDTO.preparationMethods());
        // recipe.setIngredients(recipeDTO.ingredients());
        // recipe.setCategories(recipeDTO.categories());
        recipe.setComments(recipeDTO.comments());
        recipeRepository.save(recipe);

        return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getAuthor().getId(),
                recipe.getPreparationMethods(), recipe.getRating(), recipe.getComments());
    }

    @Override
    public void deleteRecipe(UUID recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (recipe == null) {
            throw new RuntimeException("User not found");
        }

        recipeRepository.deleteById(recipeId);
    }

    @Override
    public RecipeDTO getRecipeByName(String name) {
        Recipe recipe = recipeRepository.findByName(name);

        if (recipe == null) {
            throw new RuntimeException("Recipe not found");
        }

        Double avg = ratingRepository.findAverageGradeByRecipeId(recipe.getId());
        recipe.setRating(avg != null ? avg : 0.0);

        return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getAuthor().getId(),
                recipe.getPreparationMethods(), recipe.getRating(),
                recipe.getComments());
    }

    @Override
    public RecipeDTO getRecipeById(UUID id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        Double avg = ratingRepository.findAverageGradeByRecipeId(recipe.getId());
        recipe.setRating(avg != null ? avg : 0.0);

        return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getAuthor().getId(),
                recipe.getPreparationMethods(), recipe.getRating(),
                recipe.getComments());

    }

    // @Override
    // public List<RecipeDTO> getRecipesByCategory(String category) {
    // List<Recipe> recipes = recipeRepository.findByCategoriesContaining(category);

    // if (recipes.isEmpty()) {
    // throw new RuntimeException("No recipes found");
    // }

    // return new ArrayList<>(recipes.stream()
    // .map(recipe -> new RecipeDTO(recipe.getId(), recipe.getName(),
    // recipe.getAuthor().getId(),
    // recipe.getPreparationMethods(), recipe.getRating(), recipe.getComments()))
    // .toList());
    // }

    // @Override
    // public List<RecipeDTO> getRecipesByIngredients(List<String> ingredients) {
    // List<Recipe> recipes = recipeRepository.findByIngredientsIn(ingredients);

    // if (recipes.isEmpty()) {
    // throw new RuntimeException("No recipes found");
    // }

    // return new ArrayList<>(recipes.stream()
    // .map(recipe -> new RecipeDTO(recipe.getId(), recipe.getName(),
    // recipe.getAuthor().getId(),
    // recipe.getPreparationMethods(), recipe.getRating(), recipe.getComments()))
    // .toList());
    // }

    @Override
    public List<RecipeDTO> getRecipesByUserId(UUID authorId) {
        List<Recipe> recipes = recipeRepository.findByAuthorId(authorId);

        if (recipes.isEmpty()) {
            throw new RuntimeException("No recipes found");
        }

        return new ArrayList<>(recipes.stream()
                .map(recipe -> new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getAuthor().getId(),
                        recipe.getPreparationMethods(), recipe.getRating(), recipe.getComments()))
                .toList());
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();

        if (recipes.isEmpty()) {
            throw new RuntimeException("No recipes found");
        }

        return recipes.stream().map(recipe -> {
            Double avg = ratingRepository.findAverageGradeByRecipeId(recipe.getId());
            recipe.setRating(avg != null ? avg : 0.0);

            return new RecipeDTO(
                    recipe.getId(),
                    recipe.getName(),
                    recipe.getAuthor().getId(),
                    recipe.getPreparationMethods(),
                    recipe.getRating(),
                    recipe.getComments());
        }).toList();
    }
}
