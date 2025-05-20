package com.ufscar.pooa.backend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private Float rating;

    @Column(nullable = false)
    private Date createdAt;

    @ElementCollection
    private List<String> categories;

    @ElementCollection
    private List<String> ingredients;

    @ElementCollection
    private List<String> comments;

    public Recipe() {
    }

    public Recipe(String name, String preparationMethods) {
        this.name = name;
        this.preparationMethods = preparationMethods;
        this.createdAt = new Date();
        this.ingredients = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.comments = new ArrayList<>();
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

     public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
