package com.ufscar.pooa.backend.dto.Comment;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record CommentCreateDTO(
    @NotBlank String content,
    @NotBlank UUID authorId,
    @NotBlank UUID recipeId
) {
}
