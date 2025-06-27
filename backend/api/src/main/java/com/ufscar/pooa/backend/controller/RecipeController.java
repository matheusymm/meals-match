package com.ufscar.pooa.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.pooa.backend.dto.Recipe.RecipeCreateDTO;
import com.ufscar.pooa.backend.dto.Recipe.RecipeDetailDTO;
import com.ufscar.pooa.backend.service.interfaces.IRecipeService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private IRecipeService recipeService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recipe created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Void> createRecipe(@RequestBody RecipeCreateDTO recipeCreateDTO) {
        recipeService.createRecipe(recipeCreateDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipes retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No recipes found")
    })
    public ResponseEntity<List<RecipeDetailDTO>> getAllRecipes() {
        List<RecipeDetailDTO> recipes = recipeService.getAllRecipes();

        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recipes);
    }
}
