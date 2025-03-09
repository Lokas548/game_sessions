package com.mironov.sessions_app.DTO.request;


import java.util.List;

public class UpdateUserGamesRequest {

    List<GameRank> gameRankList;


    public List<GameRank> getGameRankList() {
        return gameRankList;
    }

    public void setGameRankList(List<GameRank> gameRankList) {
        this.gameRankList = gameRankList;
    }

    public static class GameRank{
        public Long gameId;
        public String rank;
    }
}

