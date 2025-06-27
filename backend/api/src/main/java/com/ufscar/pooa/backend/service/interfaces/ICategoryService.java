package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.Category.CategoryDetailDTO;
import com.ufscar.pooa.backend.dto.Category.CategoryCreateDTO;
import com.ufscar.pooa.backend.model.Category;

public interface ICategoryService {

    CategoryDetailDTO createCategory(CategoryCreateDTO categoryCreateDTO);

    CategoryDetailDTO updateCategory(CategoryDetailDTO categoryDetailDTO);

    void deleteCategory(UUID id);

    CategoryDetailDTO getCategoryById(UUID id);

    CategoryDetailDTO getCategoryByName(String name);

    List<CategoryDetailDTO> getAllCategories();

    List<CategoryDetailDTO> getCategoriesByNameOrCreate(List<String> names);

    Category getCategoryEntityById(UUID id);
}