package com.ufscar.pooa.backend.dto;

import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record UserDTO(
        UUID id,
        @NotBlank String username,
        @NotBlank String password,
        @Email String email,
        @NotBlank String name,
        @NotBlank String phone,
        @NotBlank UserEnum role) {
    public UserDTO(UUID id, String username, String password, String email, String name, String phone, UserEnum role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public static UserDTO fromEntity(User entity) {
        return new UserDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getName(),
                entity.getPhone(),
                entity.getPassword(),
                entity.getRole());
    }
}