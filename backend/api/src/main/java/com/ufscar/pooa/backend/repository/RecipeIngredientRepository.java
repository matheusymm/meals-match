package com.ufscar.pooa.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufscar.pooa.backend.model.RecipeIngredient;


public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, UUID> {
    


}
