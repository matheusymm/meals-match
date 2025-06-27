package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.Rating.RatingCreateDTO;
import com.ufscar.pooa.backend.dto.Rating.RatingDetailDTO;

public interface IRatingService {

    RatingDetailDTO createRating(RatingCreateDTO ratingCreateDTO);

    RatingDetailDTO updateRating(UUID ratingId, RatingCreateDTO ratingCreateDTO);

    void deleteRating(UUID ratingId);

    List<RatingDetailDTO> getRatingsByRecipeId(UUID recipeId);

    List<RatingDetailDTO> getRatingsByUserId(UUID authorId);

    Double getAverageRatingOfRecipe(UUID recipeId);

}
