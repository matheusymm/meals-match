package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.CategoryDTO;
import com.ufscar.pooa.backend.model.Category;

public interface ICategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(UUID id);

    CategoryDTO getCategoryByName(String name);

    List<CategoryDTO> getAllCategories();

    List<Category> getCategoriesByNameOrCreate(List<String> names);

}