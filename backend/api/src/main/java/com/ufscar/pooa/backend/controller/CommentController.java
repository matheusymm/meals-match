package com.ufscar.pooa.backend.controller;

import com.ufscar.pooa.backend.dto.Comment.CommentCreateDTO;
import com.ufscar.pooa.backend.dto.Comment.CommentDetailDTO;
import com.ufscar.pooa.backend.service.interfaces.ICommentService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private ICommentService commentService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    })
    public ResponseEntity<Void> createComment(@RequestBody CommentCreateDTO commentCreateDTO) {
        commentService.createComment(commentCreateDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/recipe/{recipeId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CommentDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No comments found", content = @Content), })
    public ResponseEntity<List<CommentDetailDTO>> getCommentsByRecipeId(@PathVariable UUID recipeId) {
        List<CommentDetailDTO> comments = commentService.getCommentsByRecipeId(recipeId);

        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(comments);
    }
}
