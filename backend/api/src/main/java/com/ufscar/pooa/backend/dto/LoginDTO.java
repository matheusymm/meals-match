package com.ufscar.pooa.backend.dto;

public record LoginDTO(
        String email,
        String password) {
    public LoginDTO {
    }
}
