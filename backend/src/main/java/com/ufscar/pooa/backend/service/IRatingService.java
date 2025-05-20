package com.ufscar.pooa.backend.service;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.RatingDTO;

public interface IRatingService {

    RatingDTO createRating(RatingDTO ratingDTO);

    RatingDTO updateRating(UUID ratingId, RatingDTO ratingDTO);

    void deleteRating(UUID ratingId);

    List<RatingDTO> getRatingsByRecipeId(UUID recipeId);

    List<RatingDTO> getRatingsByUserId(UUID authorId);

    Double getAverageRatingOfRecipe(UUID recipeId);
    
}
