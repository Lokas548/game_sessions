package com.mironov.sessions_app.service;

import com.mironov.sessions_app.entity.LobbyEntity;
import com.mironov.sessions_app.entity.LobbyMemberEntity;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.repository.LobbyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LobbyService {

    private final LobbyRepository lobbyRepository;
    private final UserService userService;
    private final LobbyMemberService lobbyMemberService;

    public LobbyService(LobbyRepository lobbyRepository, UserService userService, @Lazy LobbyMemberService lobbyMemberService) {
        this.lobbyRepository = lobbyRepository;
        this.userService = userService;
        this.lobbyMemberService = lobbyMemberService;
    }

    public LobbyEntity getLobbyById(Long lobbyId) {
        return lobbyRepository.findById(lobbyId)
                .orElseThrow(() -> new EntityNotFoundException("Lobby not found with id: " + lobbyId));
    }

    public LobbyEntity createLobby(LobbyEntity lobbyParams){

        //TODO Сделать гибкое создание лобби

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userService.loadUserByEmail(authentication.getName());
        LobbyEntity lobby = new LobbyEntity(1L,
                lobbyParams.getName(),
                userEntity,
                LocalDateTime.now().toString(),
                1,
                lobbyParams.isCompetitive());

        lobbyMemberService.joinLobby(new LobbyMemberEntity(lobby,userEntity,LocalDateTime.now().toString()));

        return lobbyRepository.save(lobby);
    }
}
