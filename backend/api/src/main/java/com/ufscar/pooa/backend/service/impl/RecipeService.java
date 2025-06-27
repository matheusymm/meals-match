package com.ufscar.pooa.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.CategoryDTO;
import com.ufscar.pooa.backend.dto.CommentDTO;
import com.ufscar.pooa.backend.dto.RatingDTO;
import com.ufscar.pooa.backend.dto.RecipeDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredientDTO;
import com.ufscar.pooa.backend.events.NewRatingEvent;
import com.ufscar.pooa.backend.model.Ingredient;
import com.ufscar.pooa.backend.model.Rating;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.Category;
import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.RecipeIngredient;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.repository.CategoryRepository;
import com.ufscar.pooa.backend.repository.IngredientRepository;
import com.ufscar.pooa.backend.repository.RatingRepository;
import com.ufscar.pooa.backend.repository.RecipeRepository;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.service.interfaces.ICategoryService;
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
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {

        User author = userRepository.findById(recipeDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recipe recipe = new Recipe();

        recipe.setName(recipeDTO.name());
        recipe.setAuthor(author);
        recipe.setPreparationMethods(recipeDTO.preparationMethods());
        recipe.setRating(0.0);
        recipe.setCreatedAt(new Date());

        if (recipeDTO.ingredients() != null) {
            for (RecipeIngredientDTO ingredientData : recipeDTO.ingredients()) {

                Ingredient persistentIngredient = ingredientRepository.findByName(ingredientData.ingredientName())
                        .orElseThrow(() -> new IllegalArgumentException("Ingrediente não cadastrado no sistema: " + ingredientData.ingredientName()));
                
                RecipeIngredient newRecipeIngredient = new RecipeIngredient();
                
                newRecipeIngredient.setRecipe(recipe); 
                newRecipeIngredient.setIngredient(persistentIngredient);
                newRecipeIngredient.setQuantity(ingredientData.quantity());
                newRecipeIngredient.setUnit(ingredientData.unit());

                recipe.getIngredients().add(newRecipeIngredient);
            }
        }
        
        if (recipeDTO.categories() != null) {
            List<Category> persistentCategories = new ArrayList<>();
            
            for (CategoryDTO categoryData : recipeDTO.categories()) {
                
                Category category = categoryRepository.findByName(categoryData.name())
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(categoryData.name());
                        return categoryRepository.save(newCategory);
                    });
                
                if (!persistentCategories.contains(category)) {
                    persistentCategories.add(category);
                }
            }
            recipe.setCategories(persistentCategories);
    }

        Recipe savedRecipe = recipeRepository.save(recipe);

        return RecipeDTO.fromRecipe(savedRecipe);
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
        recipeRepository.save(recipe);

        if (recipeDTO.ingredients() != null) {
            for (RecipeIngredientDTO ingredientData : recipeDTO.ingredients()) {

                Ingredient persistentIngredient = ingredientRepository.findByName(ingredientData.ingredientName())
                        .orElseThrow(() -> new IllegalArgumentException("Ingrediente não cadastrado no sistema: " + ingredientData.ingredientName()));
                
                RecipeIngredient newRecipeIngredient = new RecipeIngredient();
                
                newRecipeIngredient.setRecipe(recipe); 
                newRecipeIngredient.setIngredient(persistentIngredient);
                newRecipeIngredient.setQuantity(ingredientData.quantity());
                newRecipeIngredient.setUnit(ingredientData.unit());

                recipe.getIngredients().add(newRecipeIngredient);
            }
        }

        if (recipeDTO.categories() != null) {
            for (CategoryDTO categoryData : recipeDTO.categories()) {

                Category newCategory = new Category();
                
                newCategory.setName(categoryData.name());

                recipe.getCategories().add(newCategory);
            }
        }

        if (recipeDTO.comments() != null) {
            for (CommentDTO commentData : recipeDTO.comments()) {

                Comment newComment = new Comment();
                
                newComment.setAuthor(author);
                newComment.setContent(commentData.content());
                newComment.setRecipe(recipe);

                recipe.getComments().add(newComment);
            }
        }

        return RecipeDTO.fromRecipe(recipe);
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

        return RecipeDTO.fromRecipe(recipe);
    }

    @Override
    public RecipeDTO getRecipeById(UUID id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        Double avg = ratingRepository.findAverageGradeByRecipeId(recipe.getId());
        recipe.setRating(avg != null ? avg : 0.0);

        return RecipeDTO.fromRecipe(recipe);

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
    public List<RecipeDTO> getRecipesByUserId(UUID authorId) {
        List<Recipe> recipes = recipeRepository.findByAuthorId(authorId);

        if (recipes.isEmpty()) {
            throw new RuntimeException("No recipes found");
        }

        return new ArrayList<>(recipes.stream()
                .map(RecipeDTO::fromRecipe)
                .toList());
    }

    @Override
    @Transactional
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();

        if (recipes.isEmpty()) {
            throw new RuntimeException("No recipes found");
        }

        return recipes.stream().map(recipe -> {
            Double avg = ratingRepository.findAverageGradeByRecipeId(recipe.getId());
            recipe.setRating(avg != null ? avg : 0.0);

            return RecipeDTO.fromRecipe(recipe);
        }).toList();
    }
}
