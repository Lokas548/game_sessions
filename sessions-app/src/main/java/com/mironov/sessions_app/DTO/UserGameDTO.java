package com.mironov.sessions_app.DTO;

public class UserGameDTO {
    private Long id;
    private UserDTO user;
    private GameDTO game;
    private String rankValue;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }

    public String getRankValue() {
        return rankValue;
    }

    public void setRankValue(String rankValue) {
        this.rankValue = rankValue;
    }
}
