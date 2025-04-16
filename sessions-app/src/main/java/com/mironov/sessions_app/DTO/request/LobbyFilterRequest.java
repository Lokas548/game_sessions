package com.mironov.sessions_app.DTO.request;

import java.util.List;

public class LobbyFilterRequest {

    private String lobbyName;
    private List<String> gamesList;
    private boolean isRanked;

    public LobbyFilterRequest() {
    }

    public LobbyFilterRequest(String lobbyName, List<String> gamesList, boolean isRanked) {
        this.lobbyName = lobbyName;
        this.gamesList = gamesList;
        this.isRanked = isRanked;
    }

    public LobbyFilterRequest(List<String> gamesList, boolean isRanked) {
        this.isRanked = isRanked;
        this.gamesList = gamesList;
    }

    public LobbyFilterRequest(String lobbyName, boolean isRanked) {
        this.lobbyName = lobbyName;
        this.isRanked = isRanked;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public List<String> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<String> gamesList) {
        this.gamesList = gamesList;
    }

    public boolean isRanked() {
        return isRanked;
    }

    public void setRanked(boolean ranked) {
        isRanked = ranked;
    }
}
