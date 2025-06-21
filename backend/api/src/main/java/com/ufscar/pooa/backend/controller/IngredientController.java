package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.IngredientDTO;
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
    private IIngredientService IngredientService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ingredient created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Void> createRecipeIngredients(@RequestBody IngredientDTO IngredientDTO) {
        IngredientService.createIngredient(IngredientDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredients retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No ingredients found")
    })
    public ResponseEntity<List<IngredientDTO>> getAllRecipeIngredients() {
        List<IngredientDTO> recipeIngredient = IngredientService.getAllIngredients();

        if (recipeIngredient.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(recipeIngredient);
    }
}
