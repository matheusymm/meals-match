package com.ufscar.pooa.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank String username,
        @Email String email,
        @NotBlank String name,
        @NotBlank String phone) {
    public UserDTO(String username, String email, String name, String phone) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}