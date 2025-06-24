package com.ufscar.pooa.backend.model;

import com.persistence.annotation.Column;
import com.persistence.annotation.Entity;

import java.util.Date;
import java.util.UUID;

@Entity(name = "notifications") 
public class Notification {

    @Column(name = "id")
    private UUID id;

    @Column(name = "message")
    private String message;

    @Column(name = "recipient_id")
    private UUID recipientId; 

  /*  @Column(name = "created_at") 
    private Date createdAt;*/
    
    @Column(name = "is_read")
    private boolean isRead;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public UUID getRecipientId() { return recipientId; }
    public void setRecipientId(UUID recipientId) { this.recipientId = recipientId; }
   // public Date getCreatedAt() { return createdAt; }
    //public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
}

