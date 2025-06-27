package com.ufscar.pooa.backend.dto.User;


import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record UserDetailDTO(
        UUID id,
        @NotBlank String username,
        @NotBlank String password,
        @Email String email,
        @NotBlank String name,
        @NotBlank String phone,
        @NotBlank UserEnum role) {
    public UserDetailDTO(UUID id, String username, String password, String email, String name, String phone, UserEnum role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public static UserDetailDTO fromEntity(User entity) {
        return new UserDetailDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getName(),
                entity.getPhone(),
                entity.getPassword(),
                entity.getRole());
    }
}