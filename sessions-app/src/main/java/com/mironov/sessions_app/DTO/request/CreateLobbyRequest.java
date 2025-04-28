package com.mironov.sessions_app.DTO.request;

public class CreateLobbyRequest {

    private Long gameId;
    private String lobbyName;
    private Boolean isCompetitive;


    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public Boolean getCompetitive() {
        return isCompetitive;
    }

    public void setCompetitive(Boolean competitive) {
        isCompetitive = competitive;
    }
}
