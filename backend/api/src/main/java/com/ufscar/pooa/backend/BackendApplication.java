package com.ufscar.pooa.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ufscar.pooa.backend.dto.Category.CategoryCreateDTO;
import com.ufscar.pooa.backend.dto.Comment.CommentCreateDTO;
import com.ufscar.pooa.backend.dto.Ingredient.IngredientCreateDTO;
import com.ufscar.pooa.backend.dto.Rating.RatingCreateDTO;
import com.ufscar.pooa.backend.dto.Recipe.RecipeCreateDTO;
import com.ufscar.pooa.backend.dto.Recipe.RecipeDetailDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientCreateDTO;
import com.ufscar.pooa.backend.dto.User.UserCreateDTO;
import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.service.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

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
            IRatingService ratingService,
            PasswordEncoder passwordEncoder) {
        return args -> {
            try {
                // Criar usuários
                try {
                    System.out.println(
                            "Populando o banco de dados com usuários, categorias, ingredientes, receitas, comentários e avaliações...");
                    if (userService.getUserByEmail("admin@example.com") == null) {
                        System.out.println("Criando usuário: admin");
                        userService.createUser(
                                new UserCreateDTO("Admin", "admin@example.com", passwordEncoder.encode("admin"),
                                        "", UserEnum.ROLE_ADMIN));
                    }
                    if (userService.getUserByEmail("john@example.com") == null) {
                        System.out.println("Creating user: john_doe");
                        userService.createUser(
                                new UserCreateDTO("John Doe", "john@example.com", passwordEncoder.encode("password123"),
                                        "123456789", UserEnum.ROLE_COMMON));
                    }
                    if (userService.getUserByEmail("jane@example.com") == null) {
                        userService.createUser(
                                new UserCreateDTO("Jane Doe", "jane@example.com", passwordEncoder.encode("password456"),
                                        "987654321", UserEnum.ROLE_COMMON));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar usuários: " + e.getMessage());
                }

                // Criar categorias
                try {
                    if (categoryService.getCategoryByName("Dessert") == null) {
                        categoryService.createCategory(new CategoryCreateDTO("Dessert"));
                    }
                    if (categoryService.getCategoryByName("Main Course") == null) {
                        categoryService.createCategory(new CategoryCreateDTO("Main Course"));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar categorias: " + e.getMessage());
                }

                // Criar ingredientes
                try {
                    if (ingredientService.getIngredientByName("Sugar") == null) {
                        ingredientService.createIngredient(new IngredientCreateDTO("Sugar"));
                    }
                    if (ingredientService.getIngredientByName("Flour") == null) {
                        ingredientService.createIngredient(new IngredientCreateDTO("Flour"));
                    }
                    if (ingredientService.getIngredientByName("Eggs") == null) {
                        ingredientService.createIngredient(new IngredientCreateDTO("Eggs"));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar ingredientes: " + e.getMessage());
                }

                // Criar receitas
                try {
                    if (recipeService.getRecipeByName("Chocolate Cake") == null) {
                        recipeService.createRecipe(new RecipeCreateDTO(
                                "Chocolate Cake",
                                userService.getUserByEmail("john@example.com").id(),
                                "Mix ingredients and bake.",
                                List.of(
                                        new RecipeIngredientCreateDTO("Sugar", 200.0f, "grams"),
                                        new RecipeIngredientCreateDTO("Flour", 300.0f, "grams"),
                                        new RecipeIngredientCreateDTO("Eggs", 3.0f, "units")),
                                List.of("Dessert")));
                    }
                    if (recipeService.getRecipeByName("Omelette") == null) {
                        recipeService.createRecipe(new RecipeCreateDTO(
                                "Omelette",
                                userService.getUserByEmail("jane@example.com").id(),
                                "Mix eggs and pour it in the pan.",
                                List.of(
                                        new RecipeIngredientCreateDTO("Eggs", 4.0f, "units")),
                                List.of("Main Course")));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar receitas: " + e.getMessage());
                }

                // Criar comentários
                try {
                    RecipeDetailDTO recipe = recipeService.getRecipeByName("Chocolate Cake");
                    if (recipe != null && commentService.getCommentsByRecipeId(recipe.id()).isEmpty()) {
                        commentService.createComment(new CommentCreateDTO(
                                "Looks delicious!",
                                userService.getUserByEmail("jane@example.com").id(),
                                recipe.id()));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar comentários: " + e.getMessage());
                }

                // Criar avaliações
                try {
                    RecipeDetailDTO recipe = recipeService.getRecipeByName("Chocolate Cake");
                    if (recipe != null && ratingService.getRatingsByRecipeId(recipe.id()).isEmpty()) {
                        ratingService.createRating(new RatingCreateDTO(
                                userService.getUserByEmail("jane@example.com").id(),
                                recipe.id(),
                                5,
                                "Amazing recipe!"));
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
