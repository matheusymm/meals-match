package com.ufscar.pooa.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByRecipeId(UUID recipeId);

    List<Comment> findByAuthorId(UUID authorId);
}
