package com.ufscar.pooa.backend.service.interfaces;

import com.ufscar.pooa.backend.events.NewCommentEvent;
import com.ufscar.pooa.backend.events.NewRatingEvent;

public interface INotificationService {

    void handleNewRating(NewRatingEvent ratingEvent);
    void handleNewComment(NewCommentEvent commentEvent);
}
