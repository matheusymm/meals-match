package com.ufscar.pooa.backend.service;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.CommentDTO;
import com.ufscar.pooa.backend.repository.CommentRepository;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.repository.RecipeRepository;

@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        Recipe recipe = recipeRepository.findById(commentDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User author = userRepository.findById(commentDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment newComment = new Comment();
        newComment.setAuthor(author);
        newComment.setContent(commentDTO.content());
        newComment.setRecipe(recipe);
        newComment.setCreatedAt(commentDTO.createdAt());

        Comment saved = commentRepository.save(newComment);
        return CommentDTO.fromEntity(saved);
    }

    @Override
    public CommentDTO updateComment(UUID commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        Recipe recipe = recipeRepository.findById(commentDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User author = userRepository.findById(commentDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        comment.setAuthor(author);
        comment.setContent(commentDTO.content());
        comment.setRecipe(recipe);
        comment.setCreatedAt(commentDTO.createdAt());

        Comment updated = commentRepository.save(comment);
        return CommentDTO.fromEntity(updated);
    }

    @Override
    public void deleteComment(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDTO> getCommentsByRecipeId(UUID recipeId) {
        List<Comment> comments = commentRepository.findByRecipeId(recipeId);
        return comments.stream()
                .map(CommentDTO::fromEntity)
                .toList();
    }

    @Override
    public List<CommentDTO> getCommentsByUserId(UUID authorId) {
        List<Comment> comments = commentRepository.findByAuthorId(authorId);
        return comments.stream()
                .map(CommentDTO::fromEntity)
                .toList();
    }

    @Override
    public CommentDTO getCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return CommentDTO.fromEntity(comment);
    }
    
}
