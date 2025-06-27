package com.ufscar.pooa.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.Recipe.*;
import com.ufscar.pooa.backend.model.Ingredient;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.Category;
import com.ufscar.pooa.backend.model.RecipeIngredient;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.repository.CategoryRepository;
import com.ufscar.pooa.backend.repository.IngredientRepository;
import com.ufscar.pooa.backend.repository.RatingRepository;
import com.ufscar.pooa.backend.repository.RecipeRepository;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.service.interfaces.IRecipeService;

import jakarta.transaction.Transactional;

@Service
public class RecipeService implements IRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public RecipeDetailDTO createRecipe(RecipeCreateDTO recipeCreateDTO) {
        User author = userRepository.findById(recipeCreateDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recipe recipe = new Recipe();

        recipe.setName(recipeCreateDTO.name());
        recipe.setAuthor(author);
        recipe.setPreparationMethods(recipeCreateDTO.preparationMethods());
        recipe.setRating(0.0);
        recipe.setCreatedAt(new Date());

        if (recipeCreateDTO.ingredients() != null) {
            List<RecipeIngredient> persistentIngredients = recipeCreateDTO.ingredients().stream()
                    .map(ingredientData -> {
                        Ingredient persistentIngredient = ingredientRepository.findByName(ingredientData.ingredientName())
                                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + ingredientData.ingredientName()));

                        RecipeIngredient newRecipeIngredient = new RecipeIngredient();
                        newRecipeIngredient.setRecipe(recipe);
                        newRecipeIngredient.setIngredient(persistentIngredient);
                        newRecipeIngredient.setQuantity(ingredientData.quantity());
                        newRecipeIngredient.setUnit(ingredientData.unit());

                        return newRecipeIngredient;
                    })
                    .toList();

            recipe.setIngredients(persistentIngredients);
        }

        if (recipeCreateDTO.categories() != null) {
            List<Category> persistentCategories = recipeCreateDTO.categories().stream()
                    .map(categoryName -> categoryRepository.findByName(categoryName)
                            .orElseGet(() -> {
                                Category newCategory = new Category();
                                newCategory.setName(categoryName);
                                return categoryRepository.save(newCategory);
                            }))
                    .toList();

            recipe.setCategories(persistentCategories);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        return RecipeDTOFactory.toDetailDTO(savedRecipe);
    }

    @Override
    public RecipeDetailDTO updateRecipe(UUID recipeId, RecipeCreateDTO recipeCreateDTO) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        User author = userRepository.findById(recipeCreateDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        recipe.setName(recipeCreateDTO.name());
        recipe.setAuthor(author);
        recipe.setPreparationMethods(recipeCreateDTO.preparationMethods());

        if (recipeCreateDTO.ingredients() != null) {
            List<RecipeIngredient> persistentIngredients = recipeCreateDTO.ingredients().stream()
                    .map(ingredientData -> {
                        Ingredient persistentIngredient = ingredientRepository.findByName(ingredientData.ingredientName())
                                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + ingredientData.ingredientName()));

                        RecipeIngredient newRecipeIngredient = new RecipeIngredient();
                        newRecipeIngredient.setRecipe(recipe);
                        newRecipeIngredient.setIngredient(persistentIngredient);
                        newRecipeIngredient.setQuantity(ingredientData.quantity());
                        newRecipeIngredient.setUnit(ingredientData.unit());

                        return newRecipeIngredient;
                    })
                    .toList();

            recipe.setIngredients(persistentIngredients);
        }

        if (recipeCreateDTO.categories() != null) {
            List<Category> persistentCategories = recipeCreateDTO.categories().stream()
                    .map(categoryName -> categoryRepository.findByName(categoryName)
                            .orElseGet(() -> {
                                Category newCategory = new Category();
                                newCategory.setName(categoryName);
                                return categoryRepository.save(newCategory);
                            }))
                    .toList();

            recipe.setCategories(persistentCategories);
        }

        Recipe updatedRecipe = recipeRepository.save(recipe);

        return RecipeDTOFactory.toDetailDTO(updatedRecipe);
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
    public RecipeDetailDTO getRecipeByName(String name) {
        Recipe recipe = recipeRepository.findByName(name);

        if (recipe == null) {
            return null;
        }

        Double avg = ratingRepository.findAverageGradeByRecipeId(recipe.getId());
        recipe.setRating(avg != null ? avg : 0.0);

        return RecipeDTOFactory.toDetailDTO(recipe);
    }

    @Override
    public RecipeDetailDTO getRecipeById(UUID id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe == null) {
            return null;
        }

        Double avg = ratingRepository.findAverageGradeByRecipeId(recipe.getId());
        recipe.setRating(avg != null ? avg : 0.0);

        return RecipeDTOFactory.toDetailDTO(recipe);
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
    // public List<RecipeDTO> getRecipesByIngredients(List<Ingredient> ingredients)
    // {
    // List<Recipe> recipes = recipeRepository.findByIngredientsIn(ingredients);

    // if (recipes.isEmpty()) {
    // throw new RuntimeException("No recipes found");
    // }

    // return new ArrayList<>(recipes.stream()
    // .map(recipe -> new RecipeDTO(recipe.getId(), recipe.getName(),
    // recipe.getAuthor().getId(),
    // recipe.getPreparationMethods(), recipe.getRating(), recipe.getIngredients(),
    // recipe.getComments()))
    // .toList());
    // }

    @Override
    public List<RecipeDetailDTO> getRecipesByUserId(UUID authorId) {
        List<Recipe> recipes = recipeRepository.findByAuthorId(authorId);

        if (recipes.isEmpty()) {
            throw new RuntimeException("No recipes found");
        }

        return recipes.stream()
                .map(RecipeDTOFactory::toDetailDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<RecipeDetailDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();

        return recipes.stream()
                .map(recipe -> {
                    Double avg = ratingRepository.findAverageGradeByRecipeId(recipe.getId());
                    recipe.setRating(avg != null ? avg : 0.0);

                    return RecipeDTOFactory.toDetailDTO(recipe);
                })
                .toList();
    }
}
