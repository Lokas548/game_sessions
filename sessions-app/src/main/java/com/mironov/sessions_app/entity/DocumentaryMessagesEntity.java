package com.mironov.sessions_app.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class DocumentaryMessagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String messageId;

    private String userId;
    private String lobbyId;
    private String message;
    private String sendTime;


    public DocumentaryMessagesEntity(String userId, String lobbyId, String message, String sendTime) {
        this.userId = userId;
        this.lobbyId = lobbyId;
        this.message = message;
        this.sendTime = LocalDateTime.now().toString();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
