package com.ufscar.pooa.backend.dto.Rating;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record RatingCreateDTO(
    @NotNull UUID authorId,
    @NotNull UUID recipeId,
    @NotNull int grade,
    @Size(max = 255) String content
) {
}
