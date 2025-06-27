package com.ufscar.pooa.backend.dto.User;

import com.ufscar.pooa.backend.enums.UserEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
    @NotBlank String password,
    @Email String email,
    @NotBlank String name,
    @NotBlank String phone,
    @NotBlank UserEnum role
) {
}
