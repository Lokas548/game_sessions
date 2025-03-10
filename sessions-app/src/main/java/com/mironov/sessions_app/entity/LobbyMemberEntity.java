package com.mironov.sessions_app.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "lobby_members")
public class LobbyMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lobbyMemberId;
    @ManyToOne
    @JoinColumn(name = "lobby_id")
    private LobbyEntity lobby;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String joinedAt;


    public LobbyMemberEntity() {
    }

    public LobbyMemberEntity(LobbyEntity lobby, UserEntity user, String joinedAt) {
        this.lobby = lobby;
        this.user = user;
        this.joinedAt = joinedAt;
    }

    public LobbyEntity getLobby() {
        return lobby;
    }

    public void setLobby(LobbyEntity lobby) {
        this.lobby = lobby;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Long getLobbyMemberId() {
        return lobbyMemberId;
    }

    public void setLobbyMemberId(Long lobbyMemberId) {
        this.lobbyMemberId = lobbyMemberId;
    }
}
