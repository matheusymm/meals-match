package com.ufscar.pooa.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.Recipe;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.observer.Observer;
import com.ufscar.pooa.backend.observer.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.Comment.CommentDTOFactory;
import com.ufscar.pooa.backend.dto.Comment.CommentCreateDTO;
import com.ufscar.pooa.backend.dto.Comment.CommentDetailDTO;
import com.ufscar.pooa.backend.repository.CommentRepository;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.service.interfaces.ICommentService;
import com.ufscar.pooa.backend.repository.RecipeRepository;

@Service
public class CommentService implements Subject, ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    private final List<Observer> observers = new ArrayList<>();
    
    private Comment lastCreatedComment;

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

        this.lastCreatedComment = commentRepository.save(newComment);
        
        notifyObservers();

        return CommentDTOFactory.toDetailDTO(lastCreatedComment);
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
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return null;
        }
        return CommentDTOFactory.toDetailDTO(comment);
    }

     @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.lastCreatedComment);
        }
    }

}
