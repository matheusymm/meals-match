package com.ufscar.pooa.backend.dto;

import java.util.List;

public class RecipeDTO {
    
    private String name;
    private String preparationMethods;
    private List<RecipeIngredientsDTO> ingredients;
    private List<String> categories;
    private List<CommentsDTO> comments;

    // Getters e Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparationMethods() {
        return preparationMethods;
    }

    public void setPreparationMethods(String preparationMethods) {
        this.preparationMethods = preparationMethods;
    }

    public List<RecipeIngredientsDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientsDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDTO> comments) {
        this.comments = comments;
    }
    
}
