package com.ufscar.pooa.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.pooa.backend.dto.Category.CategoryDetailDTO;
import com.ufscar.pooa.backend.dto.Rating.RatingDetailDTO;
import com.ufscar.pooa.backend.dto.Category.CategoryCreateDTO;
import com.ufscar.pooa.backend.service.interfaces.ICategoryService;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    })
    public ResponseEntity<Void> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        categoryService.createCategory(categoryCreateDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RatingDetailDTO.class))) }),
            @ApiResponse(responseCode = "204", description = "No categories found", content = @Content), })
    public ResponseEntity<List<CategoryDetailDTO>> getAllCategories() {
        List<CategoryDetailDTO> categories = categoryService.getAllCategories();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categories);
    }
}
