package com.ufscar.pooa.backend.events;

import com.ufscar.pooa.backend.model.Comment;
import org.springframework.context.ApplicationEvent;


public class NewCommentEvent extends ApplicationEvent {

    private final Comment comment;

    public NewCommentEvent(Object source, Comment comment) {
        super(source);
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }
}