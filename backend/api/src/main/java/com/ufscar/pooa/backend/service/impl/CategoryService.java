package com.ufscar.pooa.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.Category.CategoryCreateDTO;
import com.ufscar.pooa.backend.dto.Category.CategoryDetailDTO;
import com.ufscar.pooa.backend.dto.Category.CategoryDTOFactory;
import com.ufscar.pooa.backend.model.Category;
import com.ufscar.pooa.backend.repository.CategoryRepository;
import com.ufscar.pooa.backend.service.interfaces.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDetailDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.name());
        Category newCategory = categoryRepository.save(category);
        return CategoryDTOFactory.toDetailDTO(newCategory);
    }

    @Override
    public CategoryDetailDTO updateCategory(CategoryDetailDTO categoryDetailDTO) {
        Category category = categoryRepository.findById(categoryDetailDTO.id())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetailDTO.name());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryDTOFactory.toDetailDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDetailDTO getCategoryById(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        return CategoryDTOFactory.toDetailDTO(category);
    }

    @Override
    public CategoryDetailDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name).orElse(null);
        if (category == null) {
            return null;
        }
        return CategoryDTOFactory.toDetailDTO(category);
    }

    @Override
    public List<CategoryDetailDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTOFactory::toDetailDTO)
                .toList();
    }

    @Override
    public List<CategoryDetailDTO> getCategoriesByNameOrCreate(List<String> names) {
        List<CategoryDetailDTO> categories = new ArrayList<>();
        for (String name : names) {
            Category category = categoryRepository.findByName(name)
                    .orElse(null);
            if (category == null) {
                category = new Category();
                category.setName(name);
                category = categoryRepository.save(category);
            }
            categories.add(CategoryDTOFactory.toDetailDTO(category));
        }
        return categories;
    }

    @Override
    public Category getCategoryEntityById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
