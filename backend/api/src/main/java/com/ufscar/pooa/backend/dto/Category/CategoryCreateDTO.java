package com.ufscar.pooa.backend.dto.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreateDTO(
    @NotBlank String name
) {
}
