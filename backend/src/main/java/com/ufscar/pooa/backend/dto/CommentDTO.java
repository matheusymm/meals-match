package com.ufscar.pooa.backend.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.model.Recipe;

public record CommentDTO(
    @NotBlank UUID id,  // Adicionar ID para facilitar atualizações
    @NotBlank String content,
    @NotBlank User author,  // ID do autor em vez da entidade
    @NotBlank Recipe recipe,  // ID da receita
    Date createdAt  // Data de criação
) {
    public static CommentDTO fromEntity(Comment entity) {
        return new CommentDTO(
            entity.getId(),
            entity.getContent(),
            entity.getAuthor(),
            entity.getRecipe(),
            entity.getCreatedAt()
        );
    }

    public Comment toEntity() {
        return new Comment(
            this.id,
            this.content,
            this.author,
            this.createdAt,
            this.recipe
        );
    }

}



