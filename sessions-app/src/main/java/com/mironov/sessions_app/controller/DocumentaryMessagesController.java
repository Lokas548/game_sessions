package com.mironov.sessions_app.controller;


import com.mironov.sessions_app.entity.DocumentaryMessagesEntity;
import com.mironov.sessions_app.repository.DocumentaryMessagesRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/message/lobby")

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


    @MessageMapping("/{lobbyId}/send-message")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Отправить сообщение")
    public ResponseEntity sendMessage(@DestinationVariable String lobbyId, @RequestBody DocumentaryMessagesEntity documentaryMessages){
        messagesRepository.save(documentaryMessages);
        messagingTemplate.convertAndSend("/lobby/room/" + lobbyId, documentaryMessages);

        return ResponseEntity.ok(documentaryMessages);
    }
}
