package com.ufscar.pooa.backend.dto.Ingredient;

import com.ufscar.pooa.backend.model.Ingredient;

public class IngredientDTOFactory {
    public static IngredientDetailDTO toDetailDTO(Ingredient ingredient) {
        return new IngredientDetailDTO(ingredient.getId(), ingredient.getName());
    }

    public static IngredientCreateDTO toCreateDTO(Ingredient ingredient) {
        return new IngredientCreateDTO(ingredient.getName());
    }
}
