package com.ufscar.pooa.backend.dto.Recipe;

import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.dto.Category.CategoryDTOFactory;
import com.ufscar.pooa.backend.dto.Ingredient.IngredientDTOFactory;
import com.ufscar.pooa.backend.dto.Comment.CommentDTOFactory;

public class RecipeDTOFactory {

    public static RecipeDetailDTO toDetailDTO(Recipe recipe) {
        return new RecipeDetailDTO(
            recipe.getId(),
            recipe.getName(),
            recipe.getAuthor().getId(),
            recipe.getPreparationMethods(),
            recipe.getRating(),
            recipe.getIngredients().stream()
                .map(ingredient -> IngredientDTOFactory.toDetailDTO(ingredient))
                .toList(),
            recipe.getCategories().stream()
                .map(category -> CategoryDTOFactory.toDetailDTO(category))
                .toList(),
            recipe.getComments().stream()
                .map(comment -> CommentDTOFactory.toDetailDTO(comment))
                .toList()
        );
    }

}
