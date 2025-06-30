package com.ufscar.pooa.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.pooa.backend.dto.Recipe.RecipeCreateDTO;
import com.ufscar.pooa.backend.dto.Recipe.RecipeDetailDTO;
import com.ufscar.pooa.backend.service.interfaces.IRecipeService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private IRecipeService recipeService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recipe created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    })
    public ResponseEntity<Void> createRecipe(@RequestBody RecipeCreateDTO recipeCreateDTO) {
        recipeService.createRecipe(recipeCreateDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipes retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecipeDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No recipes found", content = @Content), })
    public ResponseEntity<List<RecipeDetailDTO>> getAllRecipes() {
        List<RecipeDetailDTO> recipes = recipeService.getAllRecipes();

        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{recipeId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipes retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecipeDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No recipes found", content = @Content), })
    public ResponseEntity<RecipeDetailDTO> getRecipeById(@PathVariable UUID recipeId) {
        RecipeDetailDTO recipe = recipeService.getRecipeById(recipeId);

        if (recipe == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/user/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipes retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecipeDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No recipes found", content = @Content), })
    public ResponseEntity<List<RecipeDetailDTO>> getRecipesByAuthorId(@PathVariable UUID userId) {
        List<RecipeDetailDTO> recipes = recipeService.getRecipesByUserId(userId);

        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipes retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecipeDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No recipes found", content = @Content), })
    public ResponseEntity<List<RecipeDetailDTO>> getRecipesByCategory(@RequestParam List<String> categoryNames) {
        List<RecipeDetailDTO> recipes = recipeService.getRecipesByCategories(categoryNames);

        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recipes);
    }
}
