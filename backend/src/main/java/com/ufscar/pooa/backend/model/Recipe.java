package com.ufscar.pooa.backend.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.ufscar.pooa.backend.dto.CommentsDTO;
import com.ufscar.pooa.backend.dto.RecipeIngredientsDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String preparationMethods;

    @Column(nullable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredientsDTO> ingredients;

    @ElementCollection
    private List<String> categories;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentsDTO> comments;

    public Recipe() {
    }

    public Recipe(String name, String preparationMethods) {
        this.name = name;
        this.preparationMethods = preparationMethods;
        this.createdAt = new Date();
    }

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
