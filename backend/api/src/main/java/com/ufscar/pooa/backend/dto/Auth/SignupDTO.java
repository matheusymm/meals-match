package com.ufscar.pooa.backend.dto.Auth;

import com.ufscar.pooa.backend.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupDTO(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "E-mail is required") @Email String email,
        @NotBlank(message = "Password is required") String password,
        @NotBlank(message = "Phone is required") String phone) {
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