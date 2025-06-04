package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.RecipeIngredientDTO;
import com.ufscar.pooa.backend.service.RecipeIngredientService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipeIngredient")
public class RecipeIngredientController {

    @Autowired
    private RecipeIngredientService recipeIngredientService;

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "RecipeIngredients created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Void> createRecipeIngredients(@RequestBody RecipeIngredientDTO recipeIngredientDTO) {
        recipeIngredientService.createRecipeIngredient(recipeIngredientDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "RecipeIngredientss retrieved successfully for the recipe"),
        @ApiResponse(responseCode = "204", description = "No recipe ingredients found for this recipe")
    })
    public ResponseEntity<List<RecipeIngredientDTO>> getAllRecipeIngredients() {
        List<RecipeIngredientDTO> recipeIngredient = recipeIngredientService.getAllRecipeIngredients();

        if (recipeIngredient.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recipeIngredient);
    }
}
