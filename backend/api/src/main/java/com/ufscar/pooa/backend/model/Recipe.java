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

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String preparationMethods;

    @Transient
    private Double rating;

    @Column(nullable = false)
    private Date createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

    @OneToMany(
    mappedBy = "recipe",
    cascade = CascadeType.ALL, 
    orphanRemoval = true ,
    fetch = FetchType.EAGER
    )
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Recipe() {
    }

    public Recipe(String name, User author, String preparationMethods, List<RecipeIngredient> ingredients) {
        this.name = name;
        this.author = author;
        this.preparationMethods = preparationMethods;
        this.createdAt = new Date();
        this.ingredients = new ArrayList<RecipeIngredient>();
        this.categories = new ArrayList<>();
        this.comments = new ArrayList<Comment>();
    }

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

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        }
        this.categories.add(category);
    }
}
