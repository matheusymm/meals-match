package com.ufscar.pooa.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ufscar.pooa.backend.dto.*;
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
                    if (userService.getUserByEmail("john@example.com") == null) {
                        UserDTO user1 = new UserDTO(null, "John Doe", "john@example.com", "password123",
                                "123456789", UserEnum.COMMON);
                        userService.createUser(user1);
                    }
                    if (userService.getUserByEmail("jane@example.com") == null) {
                        UserDTO user2 = new UserDTO(null, "Jane Doe", "jane@example.com", "password456",
                                "987654321", UserEnum.COMMON);
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
                                userService.getUserByEmail("john@example.com").id(),
                                "Mix ingredients and bake.",
                                5.0,
                                new ArrayList<>(),
                                new ArrayList<>(),
                                new ArrayList<>());
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
                                userService.getUserByEmail("jane@example.com").id(),
                                recipe.id(),
                                null);
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
                                userService.getUserByEmail("jane@example.com").id(),
                                recipe.id(),
                                5,
                                "Amazing recipe!");
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
