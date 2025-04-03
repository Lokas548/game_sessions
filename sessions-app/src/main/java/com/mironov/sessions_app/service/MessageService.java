package com.mironov.sessions_app.service;

import com.mironov.sessions_app.entity.MessageEntity;
import com.mironov.sessions_app.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(MessageEntity message) {
        messageRepository.save(message);
    }
}
