package com.ufscar.pooa.backend.dto;

import com.ufscar.pooa.backend.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String phone) {
    public SignupDTO(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public static SignupDTO fromEntity(User entity) {
        return new SignupDTO(
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getPhone());
    }
}