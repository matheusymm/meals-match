package com.ufscar.pooa.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    Recipe findByName(String name);

    List<Recipe> findByAuthorId(UUID authorId);

    @Query("SELECT r FROM Recipe r JOIN r.categories rc WHERE rc.name IN :names GROUP BY r.id HAVING COUNT(DISTINCT rc.id) = :count")
    List<Recipe> findRecipesByCategoryNames(
            @Param("names") List<String> categoryNames,
            @Param("count") Long categoryCount);

    @Query("SELECT r FROM Recipe r JOIN r.ingredients ri JOIN ri.ingredient i WHERE i.name IN :names GROUP BY r.id HAVING COUNT(DISTINCT i.id) = :count")
    List<Recipe> findRecipesByIngredientNames(
            @Param("names") List<String> ingredientNames,
            @Param("count") Long ingredientCount);

}