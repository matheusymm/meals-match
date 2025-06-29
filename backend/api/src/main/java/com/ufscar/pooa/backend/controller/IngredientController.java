package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.Ingredient.IngredientCreateDTO;
import com.ufscar.pooa.backend.dto.Ingredient.IngredientDetailDTO;
import com.ufscar.pooa.backend.service.interfaces.IIngredientService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IIngredientService ingredientService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ingredient created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    })
    public ResponseEntity<Void> createIngredient(@RequestBody IngredientCreateDTO ingredientCreateDTO) {
        ingredientService.createIngredient(ingredientCreateDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredients retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = IngredientDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No ingredients found", content = @Content), })
    public ResponseEntity<List<IngredientDetailDTO>> getAllIngredients() {
        List<IngredientDetailDTO> ingredients = ingredientService.getAllIngredients();

        if (ingredients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ingredients);
    }
}
