package com.ufscar.pooa.backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

   /*  @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;*/

    @Column(nullable = false)
    private String ingredient;

    @Column(nullable = false)
    private float quantity;

    @Column(nullable = false)
    private String unit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Recipe recipe, String ingredient, float quantity, String unit) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}