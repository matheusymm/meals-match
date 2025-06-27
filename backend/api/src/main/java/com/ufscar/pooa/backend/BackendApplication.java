package com.ufscar.pooa.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ufscar.pooa.backend.dto.*;
import com.ufscar.pooa.backend.dto.Category.CategoryDTO;
import com.ufscar.pooa.backend.dto.Comment.CommentDTO;
import com.ufscar.pooa.backend.dto.Ingredient.IngredientDTO;
import com.ufscar.pooa.backend.dto.Rating.RatingDTO;
import com.ufscar.pooa.backend.dto.Recipe.RecipeDTO;
import com.ufscar.pooa.backend.dto.User.UserDTO;
import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.service.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner populateDatabase(
            IUserService userService,
            ICategoryService categoryService,
            IIngredientService ingredientService,
            IRecipeService recipeService,
            ICommentService commentService,
            IRatingService ratingService) {
        return args -> {
            try {
                // Criar usuários
                try {
                    if (userService.getUserByUsername("john_doe") == null) {
                        UserDTO user1 = new UserDTO(null, "john_doe", "password123", "john@example.com", "John Doe", "123456789", UserEnum.COMMON);
                        userService.createUser(user1);
                    }
                    if (userService.getUserByUsername("jane_doe") == null) {
                        UserDTO user2 = new UserDTO(null, "jane_doe", "password456", "jane@example.com", "Jane Doe", "987654321", UserEnum.COMMON);
                        userService.createUser(user2);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar usuários: " + e.getMessage());
                }

                // Criar categorias
                try {
                    if (categoryService.getCategoryByName("Dessert") == null) {
                        categoryService.createCategory(new CategoryDTO(null, "Dessert"));
                    }
                    if (categoryService.getCategoryByName("Main Course") == null) {
                        categoryService.createCategory(new CategoryDTO(null, "Main Course"));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar categorias: " + e.getMessage());
                }

                // Criar ingredientes
                try {
                    if (ingredientService.getIngredientByName("Sugar") == null) {
                        ingredientService.createIngredient(new IngredientDTO(null, "Sugar"));
                    }
                    if (ingredientService.getIngredientByName("Flour") == null) {
                        ingredientService.createIngredient(new IngredientDTO(null, "Flour"));
                    }
                    if (ingredientService.getIngredientByName("Eggs") == null) {
                        ingredientService.createIngredient(new IngredientDTO(null, "Eggs"));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar ingredientes: " + e.getMessage());
                }

                // Criar receitas
                try {
                    if (recipeService.getRecipeByName("Chocolate Cake") == null) {
                        RecipeDTO recipe1 = new RecipeDTO(
                                null,
                                "Chocolate Cake",
                                userService.getUserByUsername("john_doe").id(),
                                "Mix ingredients and bake.",
                                5.0,
                                new ArrayList<>(),
                                List.of(categoryService.getCategoryByName("Dessert")),
                                new ArrayList<>()
                        );
                        recipeService.createRecipe(recipe1);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar receitas: " + e.getMessage());
                }

                // Criar comentários
                try {
                    RecipeDTO recipe = recipeService.getRecipeByName("Chocolate Cake");
                    if (recipe != null && commentService.getCommentsByRecipeId(recipe.id()).isEmpty()) {
                        CommentDTO comment1 = new CommentDTO(
                                null,
                                "Looks delicious!",
                                userService.getUserByUsername("jane_doe").id(),
                                recipe.id(),
                                null
                        );
                        commentService.createComment(comment1);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar comentários: " + e.getMessage());
                }

                // Criar avaliações
                try {
                    RecipeDTO recipe = recipeService.getRecipeByName("Chocolate Cake");
                    if (recipe != null && ratingService.getRatingsByRecipeId(recipe.id()).isEmpty()) {
                        RatingDTO rating1 = new RatingDTO(
                                null,
                                userService.getUserByUsername("jane_doe").id(),
                                recipe.id(),
                                5,
                                "Amazing recipe!"
                        );
                        ratingService.createRating(rating1);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar avaliações: " + e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("Erro geral ao popular o banco de dados: " + e.getMessage());
            }
        };
    }
}
