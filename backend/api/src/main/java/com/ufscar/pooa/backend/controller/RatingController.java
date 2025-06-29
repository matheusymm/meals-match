package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.Rating.RatingCreateDTO;
import com.ufscar.pooa.backend.dto.Rating.RatingDetailDTO;
import com.ufscar.pooa.backend.service.interfaces.IRatingService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private IRatingService ratingService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rating created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    })
    public ResponseEntity<Void> createRating(@RequestBody RatingCreateDTO ratingCreateDTO) {
        ratingService.createRating(ratingCreateDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/recipe/{recipeId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ratings retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RatingDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No ratings found", content = @Content), })
    public ResponseEntity<List<RatingDetailDTO>> getRatingsByRecipeId(@PathVariable UUID recipeId) {
        List<RatingDetailDTO> ratings = ratingService.getRatingsByRecipeId(recipeId);

        if (ratings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ratings);
    }
}
