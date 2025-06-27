package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.Comment.CommentCreateDTO;
import com.ufscar.pooa.backend.dto.Comment.CommentDetailDTO;

public interface ICommentService {
    CommentDetailDTO createComment(CommentCreateDTO commentCreateDTO);

    CommentDetailDTO updateComment(UUID commentId, CommentCreateDTO commentCreateDTO);

    void deleteComment(UUID commentId);

    List<CommentDetailDTO> getCommentsByRecipeId(UUID recipeId);

    List<CommentDetailDTO> getCommentsByUserId(UUID authorId);

    CommentDetailDTO getCommentById(UUID commentId);

}