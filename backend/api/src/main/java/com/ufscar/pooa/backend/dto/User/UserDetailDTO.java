package com.ufscar.pooa.backend.dto.User;


import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record UserDetailDTO(
        UUID id,
        @NotBlank String name,
        @Email String email,
        @NotBlank String password,
        @NotBlank String phone,
        @NotBlank UserEnum role) {
    public UserDetailDTO(UUID id, String name, String email, String password, String phone, UserEnum role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public static UserDetailDTO fromEntity(User entity) {
        return new UserDetailDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPassword(),
                entity.getRole());
    }
}