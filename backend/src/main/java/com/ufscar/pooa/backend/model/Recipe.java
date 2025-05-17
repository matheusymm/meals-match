package com.ufscar.pooa.backend.model;

import jakarta.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String preparationMethods;

    // Construtor padrão
    public Recipe() {
    }

    // Construtor com campos
    public Recipe(String name, String preparationMethods) {
        this.name = name;
        this.preparationMethods = preparationMethods;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

