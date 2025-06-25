package com.ufscar.pooa.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.CategoryDTO;
import com.ufscar.pooa.backend.model.Category;
import com.ufscar.pooa.backend.repository.CategoryRepository;
import com.ufscar.pooa.backend.service.interfaces.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        var category = new Category();
        category.setName(categoryDTO.name());
        categoryRepository.save(category);
        return CategoryDTO.fromCategory(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        var category = categoryRepository.findById(categoryDTO.id())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDTO.name());
        categoryRepository.save(category);
        return CategoryDTO.fromCategory(category);
    }

    @Override
    public void deleteCategory(CategoryDTO categoryDTO) {
        var category = categoryRepository.findById(categoryDTO.id())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategoryById(UUID id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryDTO.fromCategory(category);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        var category = categoryRepository.findByName(name);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        return CategoryDTO.fromCategory(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::fromCategory)
                .toList();
    }

    @Override
    public List<Category> getCategoriesByNameOrCreate(List<String> names) {
        List<Category> categories = new ArrayList<>();
        for (String name : names) {
            Category category = categoryRepository.findByName(name);
            if (category == null) {
                category = new Category();
                category.setName(name);
                categoryRepository.save(category);
            }
            categories.add(category);
        }
        return categories;
    }
}
