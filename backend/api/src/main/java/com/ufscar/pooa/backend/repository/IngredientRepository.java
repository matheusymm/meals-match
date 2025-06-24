package com.ufscar.pooa.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {

    Ingredient findByName(String name);

}