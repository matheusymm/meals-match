package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.CommentDTO;

public interface ICommentService {
    CommentDTO createComment(CommentDTO commentDTO);

    CommentDTO updateComment(UUID commentId, CommentDTO commentDTO);

    void deleteComment(UUID commentId);

    List<CommentDTO> getCommentsByRecipeId(UUID recipeId);

    List<CommentDTO> getCommentsByUserId(UUID authorId);

    CommentDTO getCommentById(UUID commentId);

}