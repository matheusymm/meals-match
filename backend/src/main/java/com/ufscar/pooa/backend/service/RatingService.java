package com.ufscar.pooa.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.RatingDTO;
import com.ufscar.pooa.backend.model.Rating;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.repository.RatingRepository;
import com.ufscar.pooa.backend.repository.RecipeRepository;
import com.ufscar.pooa.backend.repository.UserRepository;

@Service
public class RatingService implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RatingDTO createRating(RatingDTO ratingDTO){

        Recipe recipe = recipeRepository.findById(ratingDTO.recipeId())
            .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User author = userRepository.findById(ratingDTO.authorId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Rating rating = new Rating();

        rating.setRecipe(recipe);
        rating.setAuthor(author);
        rating.setGrade(ratingDTO.grade());
        rating.setContent(ratingDTO.content());

        Rating savedRating = ratingRepository.save(rating);

        return new RatingDTO(savedRating.getRecipe().getId(), savedRating.getAuthor().getId(), savedRating.getGrade(), savedRating.getContent());
    }

    @Override
    public RatingDTO updateRating(UUID ratingId, RatingDTO ratingDTO) {
        Rating rating = ratingRepository.findById(ratingId)
            .orElseThrow(() -> new RuntimeException("Rating not found"));

        Recipe recipe = recipeRepository.findById(ratingDTO.recipeId())
            .orElseThrow(() -> new RuntimeException("Recipe not found"));

        User author = userRepository.findById(ratingDTO.authorId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        rating.setRecipe(recipe);
        rating.setAuthor(author);
        rating.setGrade(ratingDTO.grade());
        rating.setContent(ratingDTO.content());

        Rating savedRating = ratingRepository.save(rating);

        return new RatingDTO(savedRating.getRecipe().getId(), savedRating.getAuthor().getId(), savedRating.getGrade(), savedRating.getContent());
    }

    @Override
    public void deleteRating(UUID ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElse(null);

        if (rating == null) {
            throw new RuntimeException("User not found");
        }

        ratingRepository.deleteById(ratingId);
    }

    @Override
    public List<RatingDTO> getRatingsByRecipeId(UUID recipeId) {
        List<Rating> ratings = ratingRepository.findByRecipeId(recipeId);
    
        return ratings.stream()
            .map(rating -> new RatingDTO(
                rating.getRecipe().getId(),
                rating.getAuthor().getId(),
                rating.getGrade(),
                rating.getContent()
            ))
            .toList();
    }

    @Override
    public List<RatingDTO> getRatingsByUserId(UUID authorId) {
        List<Rating> ratings = ratingRepository.findByAuthorId(authorId);
    
        return ratings.stream()
            .map(rating -> new RatingDTO(
                rating.getRecipe().getId(),
                rating.getAuthor().getId(),
                rating.getGrade(),
                rating.getContent()
            ))
            .toList();
    }

    @Override
    public Double getAverageRatingOfRecipe(UUID recipeId) {
        Double averageRating = ratingRepository.findAverageGradeByRecipeId(recipeId);

        if (averageRating == null) {
            return null; 
        }
    
        return Math.floor(averageRating * 100) / 100;
    }

    
}
