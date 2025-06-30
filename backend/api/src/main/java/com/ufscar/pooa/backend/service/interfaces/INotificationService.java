package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;

import com.ufscar.pooa.backend.dto.Notification.NotificationDetailDTO;
import com.ufscar.pooa.backend.model.Comment;
import com.ufscar.pooa.backend.model.Rating;

public interface INotificationService {

    void handleNewRating(Rating rating);

    void handleNewComment(Comment comment);

    List<NotificationDetailDTO> getAllNotifications();
}
