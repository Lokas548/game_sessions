package com.mironov.sessions_app.DTO;

public class LobbyMemberDTO {
    private Long id;
    private LobbyDTO lobby;
    private UserDTO user;
    private String joinedAt;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LobbyDTO getLobby() {
        return lobby;
    }

    public void setLobby(LobbyDTO lobby) {
        this.lobby = lobby;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }
}
