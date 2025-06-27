package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.Ingredient.IngredientCreateDTO;
import com.ufscar.pooa.backend.dto.Ingredient.IngredientDetailDTO;
import com.ufscar.pooa.backend.service.interfaces.IIngredientService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IIngredientService ingredientService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ingredient created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Void> createIngredient(@RequestBody IngredientCreateDTO ingredientCreateDTO) {
        ingredientService.createIngredient(ingredientCreateDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredients retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No ingredients found")
    })
    public ResponseEntity<List<IngredientDetailDTO>> getAllIngredients() {
        List<IngredientDetailDTO> ingredients = ingredientService.getAllIngredients();

        if (ingredients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ingredients);
    }
}
