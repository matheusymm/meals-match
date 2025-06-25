package com.ufscar.pooa.backend.events;

import com.ufscar.pooa.backend.model.Rating;
import org.springframework.context.ApplicationEvent;


public class NewRatingEvent extends ApplicationEvent {

    private final Rating rating;

    public NewRatingEvent(Object source, Rating rating) {
        
        super(source);
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }
}