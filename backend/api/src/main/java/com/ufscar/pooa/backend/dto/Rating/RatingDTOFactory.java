package com.ufscar.pooa.backend.dto.Rating;

import com.ufscar.pooa.backend.model.Rating;

public class RatingDTOFactory {

    public static RatingDetailDTO toDetailDTO(Rating rating) {
        return new RatingDetailDTO(
            rating.getId(),
            rating.getAuthor().getId(),
            rating.getRecipe().getId(),
            rating.getGrade(),
            rating.getContent()
        );
    }

}
