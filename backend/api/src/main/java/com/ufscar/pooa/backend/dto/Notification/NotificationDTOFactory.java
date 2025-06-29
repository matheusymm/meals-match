package com.ufscar.pooa.backend.dto.Notification;

import com.ufscar.pooa.backend.model.Notification;

public class NotificationDTOFactory {
    public static NotificationDetailDTO toDetailDTO(Notification notification) {
        return new NotificationDetailDTO(
                notification.getId(),
                notification.getMessage(),
                notification.getRecipientId(),
                notification.isRead());
    }

    public static NotificationCreateDTO toCreateDTO(Notification notification) {
        return new NotificationCreateDTO(
                notification.getMessage(),
                notification.getRecipientId());
    }
}
