package com.mironov.sessions_app.service;

import com.mironov.sessions_app.entity.LobbyEntity;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.repository.LobbyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LobbyService {

    private final LobbyRepository lobbyRepository;
    private final UserService userService;

    public LobbyService(LobbyRepository lobbyRepository, UserService userService) {
        this.lobbyRepository = lobbyRepository;
        this.userService = userService;
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

        return lobbyRepository.save(lobby);
    }
}
