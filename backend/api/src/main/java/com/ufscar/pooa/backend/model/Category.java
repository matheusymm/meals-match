package com.ufscar.pooa.backend.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Category() {
        this.recipes = new HashSet<>();
    }

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.recipes = new HashSet<>();
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

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    
}
