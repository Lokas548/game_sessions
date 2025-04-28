package com.mironov.sessions_app.controller;


import com.mironov.sessions_app.entity.DocumentaryMessagesEntity;
import com.mironov.sessions_app.repository.DocumentaryMessagesRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/message")

@OpenAPIDefinition()
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

@RestController
public class DocumentaryMessagesController {

    private final DocumentaryMessagesRepository messagesRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public DocumentaryMessagesController(DocumentaryMessagesRepository messagesRepository, SimpMessagingTemplate messagingTemplate) {
        this.messagesRepository = messagesRepository;
        this.messagingTemplate = messagingTemplate;
    }


    @MessageMapping("/chat/{lobbyId}/send")
    public void sendMessage(@DestinationVariable String lobbyId, DocumentaryMessagesEntity documentaryMessages) {
        documentaryMessages.setLobbyId(lobbyId);
        messagesRepository.save(documentaryMessages);
        messagingTemplate.convertAndSend("/topic/lobby/" + lobbyId, documentaryMessages); 
    }
}
