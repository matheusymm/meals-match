package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;

import com.ufscar.pooa.backend.dto.Notification.NotificationDetailDTO;
import com.ufscar.pooa.backend.events.NewCommentEvent;
import com.ufscar.pooa.backend.events.NewRatingEvent;

public interface INotificationService {

    void handleNewRating(NewRatingEvent ratingEvent);

    void handleNewComment(NewCommentEvent commentEvent);

    List<NotificationDetailDTO> getAllNotifications();
}
