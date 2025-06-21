package com.ufscar.pooa.backend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.Ingredient;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String preparationMethods;

    @Transient
    private Double rating;

    @Column(nullable = false)
    private Date createdAt;

    // @ElementCollection
    // private List<String> categories;

    @OneToMany
    private List<Ingredient> ingredients;

    @OneToMany
    private List<Comment> comments;

    public Recipe() {
    }

    public Recipe(String name, User author, String preparationMethods, List<Ingredient> ingredients) {
        this.name = name;
        this.author = author;
        this.preparationMethods = preparationMethods;
        this.createdAt = new Date();
        this.ingredients = ingredients;
        // this.categories = new ArrayList<>();
        this.comments = new ArrayList<Comment>();
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Ingredient> getIngredients() {
    return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
    }

    // public List<String> getCategories() {
    // return categories;
    // }

    // public void setCategories(List<String> categories) {
    // this.categories = categories;
    // }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
