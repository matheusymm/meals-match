package com.ufscar.pooa.backend.dto.Notification;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;

public record NotificationDetailDTO(
        UUID id,
        @NotBlank String message,
        @NotNull UUID recipientId,
        boolean isRead) {
    public NotificationDetailDTO(UUID id, String message, UUID recipientId, boolean isRead) {
        this.id = id;
        this.message = message;
        this.recipientId = recipientId;
        this.isRead = isRead;
    }
}
