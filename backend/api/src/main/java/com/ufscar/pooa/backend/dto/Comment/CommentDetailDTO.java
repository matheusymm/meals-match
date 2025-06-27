package com.ufscar.pooa.backend.dto.Comment;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

import com.ufscar.pooa.backend.model.Comment;

public record CommentDetailDTO(
    @NotBlank UUID id,  // Adicionar ID para facilitar atualizações
    @NotBlank String content,
    @NotBlank UUID authorId,  // ID do autor em vez da entidade
    @NotBlank UUID recipeId,  // ID da receita
    Date createdAt  // Data de criação
) {
    public static CommentDetailDTO fromEntity(Comment comment) {
        return new CommentDetailDTO(
            comment.getId(),
            comment.getContent(),
            comment.getAuthor().getId(),
            comment.getRecipe().getId(),
            comment.getCreatedAt()
        );
    }
}



