package com.ufscar.pooa.backend.dto.Notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record NotificationCreateDTO(
    @NotBlank String message,
    @NotNull UUID recipientId
) {
}
