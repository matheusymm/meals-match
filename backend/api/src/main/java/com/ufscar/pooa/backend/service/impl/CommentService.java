package com.ufscar.pooa.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.Comment.CommentDTOFactory;
import com.ufscar.pooa.backend.dto.Comment.CommentCreateDTO;
import com.ufscar.pooa.backend.dto.Comment.CommentDetailDTO;
import com.ufscar.pooa.backend.events.NewCommentEvent;
import com.ufscar.pooa.backend.repository.CommentRepository;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.service.interfaces.ICommentService;
import com.ufscar.pooa.backend.repository.RecipeRepository;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public CommentDetailDTO createComment(CommentCreateDTO commentCreateDTO) {
        Recipe recipe = recipeRepository.findById(commentCreateDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User author = userRepository.findById(commentCreateDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment newComment = new Comment();
        newComment.setAuthor(author);
        newComment.setContent(commentCreateDTO.content());
        newComment.setRecipe(recipe);
        newComment.setCreatedAt(new Date());

        Comment saved = commentRepository.save(newComment);

        eventPublisher.publishEvent(new NewCommentEvent(this, saved));

        return CommentDTOFactory.toDetailDTO(saved);
    }

    @Override
    public CommentDetailDTO updateComment(UUID commentId, CommentCreateDTO commentCreateDTO) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        Recipe recipe = recipeRepository.findById(commentCreateDTO.recipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User author = userRepository.findById(commentCreateDTO.authorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        comment.setAuthor(author);
        comment.setContent(commentCreateDTO.content());
        comment.setRecipe(recipe);
        comment.setCreatedAt(new Date());

        Comment updated = commentRepository.save(comment);
        return CommentDTOFactory.toDetailDTO(updated);
    }

    @Override
    public void deleteComment(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDetailDTO> getCommentsByRecipeId(UUID recipeId) {
        List<Comment> comments = commentRepository.findByRecipeId(recipeId);
        return comments.stream()
                .map(CommentDTOFactory::toDetailDTO)
                .toList();
    }

    @Override
    public List<CommentDetailDTO> getCommentsByUserId(UUID authorId) {
        List<Comment> comments = commentRepository.findByAuthorId(authorId);
        return comments.stream()
                .map(CommentDTOFactory::toDetailDTO)
                .toList();
    }

    @Override
    public CommentDetailDTO getCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return CommentDTOFactory.toDetailDTO(comment);
    }

}
