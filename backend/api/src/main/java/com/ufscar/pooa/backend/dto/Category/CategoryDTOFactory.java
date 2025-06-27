package com.ufscar.pooa.backend.dto.Category;

import com.ufscar.pooa.backend.model.Category;

public class CategoryDTOFactory {

    public static CategoryDetailDTO toDetailDTO(Category category) {
        return new CategoryDetailDTO(category.getId(), category.getName());
    }

}
