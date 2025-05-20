package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.RatingDTO;
import com.ufscar.pooa.backend.service.RatingService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Rating created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Void> createRating(@RequestBody RatingDTO ratingDTO) {
        ratingService.createRating(ratingDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/recipe/{recipeId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ratings retrieved successfully for the recipe"),
        @ApiResponse(responseCode = "204", description = "No ratings found for this recipe")
    })
    public ResponseEntity<List<RatingDTO>> getRatingsByRecipeId(@PathVariable UUID recipeId) {
        List<RatingDTO> ratings = ratingService.getRatingsByRecipeId(recipeId);

        if (ratings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ratings);
    }
}
