package com.ufscar.pooa.backend.dto.Rating;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record RatingDetailDTO(
    UUID id,
    @NotNull UUID authorId,
    @NotNull UUID recipeId,
    @NotNull int grade,
    @Size(max = 255) String content
) {
    public RatingDetailDTO(UUID id, UUID authorId, UUID recipeId, int grade, String content) {
        this.id = id;
        this.authorId = authorId;
        this.recipeId = recipeId;
        this.grade = grade;
        this.content = content;
    }
}
