package com.mironov.sessions_app.DTO;

public class UserGameDTO {
    private Integer id;
    private UserDTO user; // Включаем DTO для пользователя
    private GameDTO game; // Включаем DTO для игры
    private String rankValue;

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
