package com.mironov.sessions_app.controller;


import com.mironov.sessions_app.entity.LobbyEntity;
import com.mironov.sessions_app.entity.MessageEntity;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.service.LobbyMemberService;
import com.mironov.sessions_app.service.MessageService;
import com.mironov.sessions_app.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final LobbyMemberService lobbyMemberService;

    public MessageController(MessageService messageService, UserService userService, LobbyMemberService lobbyMemberService) {
        this.messageService = messageService;
        this.userService = userService;
        this.lobbyMemberService = lobbyMemberService;
    }


    @MessageMapping("/sendMessage")
    @SendTo("/topic/lobby/{lobbyId}")
    @Tag(name = "Отправить сообщение")
    @SecurityRequirement(name = "JWT")
    public MessageEntity sendMessage(MessageEntity message, Authentication authentication) {
        UserEntity user = userService.loadUserByEmail(authentication.getName());

        LobbyEntity lobby = lobbyMemberService.findLobbyByUser(user);

        if (lobby != null) {
            message.setUser(user);
            message.setLobby(lobby);
            messageService.saveMessage(message);
            return message;
        } else {
            throw new RuntimeException("User is not in any lobby.");
        }
    }
}