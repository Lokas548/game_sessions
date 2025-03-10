package com.mironov.sessions_app.service;

import com.mironov.sessions_app.entity.LobbyEntity;
import com.mironov.sessions_app.entity.LobbyMemberEntity;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.repository.LobbyMemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyMemberService {
    private final LobbyMemberRepository lobbyMemberRepository;

    public LobbyMemberService(LobbyMemberRepository lobbyMemberRepository, LobbyService lobbyService) {
        this.lobbyMemberRepository = lobbyMemberRepository;
    }

    public ResponseEntity joinLobby(LobbyMemberEntity lobbyMember){
        return ResponseEntity.ok(lobbyMemberRepository.save(lobbyMember));
    }

    public boolean isUserInLobby(UserEntity user, LobbyEntity lobby){
        return lobbyMemberRepository.existsByUserAndLobby(user,lobby) || lobbyMemberRepository.existsByUser(user);
    }

    public List<UserEntity> findAllUsersInLobby(LobbyEntity lobby){
        List<?> list = lobbyMemberRepository.findUsersByLobby(lobby);
        return lobbyMemberRepository.findUsersByLobby(lobby);
    }

    public void deleteUserFromLobby(UserEntity user){
        lobbyMemberRepository.deleteUserById(user.getId());
    }
}
