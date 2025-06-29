package com.ufscar.pooa.backend.dto.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "E-mail is required") @Email(message = "E-mail should be valid") String email,
        @NotBlank(message = "Password is required") String password) {
    public LoginDTO {
    }
}
