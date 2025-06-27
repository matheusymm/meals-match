package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientCreateDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientDetailDTO;
import com.ufscar.pooa.backend.service.interfaces.IRecipeIngredientService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipeIngredient")
public class RecipeIngredientController {

    @Autowired
    private IRecipeIngredientService recipeIngredientService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "RecipeIngredients created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Void> createRecipeIngredients(@RequestBody RecipeIngredientCreateDTO recipeIngredientCreateDTO) {
        recipeIngredientService.createRecipeIngredient(recipeIngredientCreateDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "RecipeIngredientss retrieved successfully for the recipe"),
            @ApiResponse(responseCode = "204", description = "No recipe ingredients found for this recipe")
    })
    public ResponseEntity<List<RecipeIngredientDetailDTO>> getAllRecipeIngredients() {
        List<RecipeIngredientDetailDTO> recipeIngredient = recipeIngredientService.getAllRecipeIngredients();

        if (recipeIngredient.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recipeIngredient);
    }
}
