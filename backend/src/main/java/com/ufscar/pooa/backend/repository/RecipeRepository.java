package com.ufscar.pooa.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {

    Recipe findByName(String name);

    List<Recipe> findByAuthorId(UUID authorId);

    List<Recipe> findByCategoriesName(String categoryName);


    // List<Recipe> findByIngredientsIn(List<String> ingredients);
}