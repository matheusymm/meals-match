package com.ufscar.pooa.backend.dto.Comment;

import com.ufscar.pooa.backend.model.Comment;

public class CommentDTOFactory {
    public static CommentDetailDTO toDetailDTO(Comment comment) {
        return new CommentDetailDTO(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor().getId(),
                comment.getRecipe().getId(),
                comment.getCreatedAt());
    }

    public static CommentCreateDTO toCreateDTO(Comment comment) {
        return new CommentCreateDTO(
                comment.getContent(),
                comment.getAuthor().getId(),
                comment.getRecipe().getId());
    }
}
