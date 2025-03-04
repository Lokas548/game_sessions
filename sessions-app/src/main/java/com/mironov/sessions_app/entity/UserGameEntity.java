package com.mironov.sessions_app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_games")
public class UserGameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGameId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
    private String rankValue;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public String getRankValue() {
        return rankValue;
    }

    public void setRankValue(String rankValue) {
        this.rankValue = rankValue;
    }

    public Long getUserGameId() {
        return userGameId;
    }

    public void setUserGameId(Long userGameId) {
        this.userGameId = userGameId;
    }
}
