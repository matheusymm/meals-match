package com.ufscar.pooa.backend.dto.RecipeIngredient;

import com.ufscar.pooa.backend.model.RecipeIngredient;

public class RecipeIngredientDTOFactory {

    public static RecipeIngredientDetailDTO toDetailDTO(RecipeIngredient recipeIngredient) {
        return new RecipeIngredientDetailDTO(
            recipeIngredient.getId(),
            recipeIngredient.getRecipe().getId(),
            recipeIngredient.getIngredient(),
            recipeIngredient.getQuantity(),
            recipeIngredient.getUnit()
        );
    }
}
