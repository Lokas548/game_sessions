package com.mironov.sessions_app.entity;
import com.mironov.sessions_app.DTO.response.FilteredLobbiesResponse;
import jakarta.persistence.*;

@Entity
@Table(name = "lobby")
public class LobbyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lobbyId;
    private Long gameId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
    private String createdAt;
    private Long currentCapacity;
    private boolean isCompetitive;

    public LobbyEntity() {

    }

    public LobbyEntity(Long gameId, String name, UserEntity owner, String createdAt, Long currentCapacity, boolean isCompetitive) {
        this.gameId = gameId;
        this.name = name;
        this.owner = owner;
        this.createdAt = createdAt;
        this.currentCapacity = currentCapacity;
        this.isCompetitive = isCompetitive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Long currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Long getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(Long lobbyId) {
        this.lobbyId = lobbyId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public boolean isCompetitive() {
        return isCompetitive;
    }

    public void setCompetitive(boolean competitive) {
        isCompetitive = competitive;
    }

    public static FilteredLobbiesResponse convertToFilteredLobbiesResponse(LobbyEntity lobby){
        return new FilteredLobbiesResponse
                (lobby.getLobbyId(),
                lobby.getName(),
                lobby.getCurrentCapacity(),
                lobby.getGameId(),
                lobby.isCompetitive());

    }

    public void increment(){
        this.currentCapacity++;
    }

    public void decrement(){
        this.currentCapacity--;
    }
}