package com.ufscar.pooa.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufscar.pooa.backend.model.Rating;


public interface RatingRepository extends JpaRepository<Rating, UUID> {
    
    List<Rating> findByRecipeId(UUID recipeId);

    List<Rating> findByAuthorId(UUID authorId);

    @Query("SELECT AVG(r.grade) FROM Rating r WHERE r.recipe.id = :recipeId")
    Double findAverageGradeByRecipeId(@Param("recipeId") UUID recipeId);

}
