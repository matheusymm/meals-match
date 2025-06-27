package com.ufscar.pooa.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.Rating.RatingCreateDTO;
import com.ufscar.pooa.backend.dto.Rating.RatingDTOFactory;
import com.ufscar.pooa.backend.dto.Rating.RatingDetailDTO;
import com.ufscar.pooa.backend.events.NewRatingEvent;
import com.ufscar.pooa.backend.model.Rating;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.repository.RatingRepository;
import com.ufscar.pooa.backend.repository.RecipeRepository;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.service.interfaces.IRatingService;

@Service
public class RatingService implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public RatingDetailDTO createRating(RatingCreateDTO ratingCreateDTO) {
        Recipe recipe = recipeRepository.findById(ratingCreateDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User author = userRepository.findById(ratingCreateDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rating rating = new Rating();

        rating.setRecipe(recipe);
        rating.setAuthor(author);
        rating.setGrade(ratingCreateDTO.grade());
        rating.setContent(ratingCreateDTO.content());
        rating.setCreatedAt(new Date());

        Rating savedRating = ratingRepository.save(rating);

        eventPublisher.publishEvent(new NewRatingEvent(this, savedRating));
        return RatingDTOFactory.toDetailDTO(savedRating);
    }

    @Override
    public RatingDetailDTO updateRating(UUID ratingId, RatingCreateDTO ratingCreateDTO) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));

        Recipe recipe = recipeRepository.findById(ratingCreateDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        User author = userRepository.findById(ratingCreateDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        rating.setRecipe(recipe);
        rating.setAuthor(author);
        rating.setGrade(ratingCreateDTO.grade());
        rating.setContent(ratingCreateDTO.content());

        Rating savedRating = ratingRepository.save(rating);

        return RatingDTOFactory.toDetailDTO(savedRating);
    }

    @Override
    public void deleteRating(UUID ratingId) {
        ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public List<RatingDetailDTO> getRatingsByRecipeId(UUID recipeId) {
        List<Rating> ratings = ratingRepository.findByRecipeId(recipeId);
        return ratings.stream().map(RatingDTOFactory::toDetailDTO).toList();
    }

    @Override
    public List<RatingDetailDTO> getRatingsByUserId(UUID authorId) {
        List<Rating> ratings = ratingRepository.findByAuthorId(authorId);
        return ratings.stream().map(RatingDTOFactory::toDetailDTO).toList();
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
