package com.ufscar.pooa.backend.dto.Recipe;

import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.dto.Category.CategoryDTOFactory;
import com.ufscar.pooa.backend.dto.RecipeIngredient.RecipeIngredientDTOFactory;
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
                .map(RecipeIngredientDTOFactory::toDetailDTO)
                .toList(),
            recipe.getCategories().stream()
                .map(CategoryDTOFactory::toDetailDTO)
                .toList(),
            recipe.getComments().stream()
                .map(CommentDTOFactory::toDetailDTO)
                .toList()
        );
    }

}
