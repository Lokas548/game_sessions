package com.mironov.sessions_app.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "game")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    private String name;
    private String genre;
    private Integer maxLobbyCapacity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getMaxLobbyCapacity() {
        return maxLobbyCapacity;
    }

    public void setMaxLobbyCapacity(Integer maxLobbyCapacity) {
        this.maxLobbyCapacity = maxLobbyCapacity;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}