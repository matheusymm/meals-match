package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.CommentDTO;
import com.ufscar.pooa.backend.service.CommentService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Void> createComment(@RequestBody CommentDTO commentDTO) {
        commentService.createComment(commentDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/recipe/{recipeId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully for the recipe"),
            @ApiResponse(responseCode = "204", description = "No Comments found for this recipe")
    })
    public ResponseEntity<List<CommentDTO>> getCommentsByRecipeId(@PathVariable UUID recipeId) {
        List<CommentDTO> comments = commentService.getCommentsByRecipeId(recipeId);

        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(comments);
    }
}
