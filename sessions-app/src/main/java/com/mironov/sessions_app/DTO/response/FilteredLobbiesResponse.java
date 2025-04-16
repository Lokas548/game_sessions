package com.mironov.sessions_app.DTO.response;

public class FilteredLobbiesResponse {

    private Long lobbyId;
    private String lobbyName;
    private Long capacity;
    private Long gameId;
    private boolean isRanked;


    public FilteredLobbiesResponse() {
    }

    public FilteredLobbiesResponse(Long lobbyId,String lobbyName, Long capacity, Long gameId, boolean isRanked) {
        this.lobbyId = lobbyId;
        this.lobbyName = lobbyName;
        this.capacity = capacity;
        this.gameId = gameId;
        this.isRanked = isRanked;
    }

    public Long getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(Long lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public boolean isRanked() {
        return isRanked;
    }

    public void setRanked(boolean ranked) {
        isRanked = ranked;
    }
}
