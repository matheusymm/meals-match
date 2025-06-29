package com.ufscar.pooa.backend.dto.Comment;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

import com.ufscar.pooa.backend.model.Comment;

public record CommentDetailDTO(
        @NotBlank UUID id,
        @NotBlank String content,
        @NotBlank UUID authorId,
        @NotBlank UUID recipeId,
        Date createdAt) {
    public static CommentDetailDTO fromEntity(Comment comment) {
        return new CommentDetailDTO(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor().getId(),
                comment.getRecipe().getId(),
                comment.getCreatedAt());
    }
}
