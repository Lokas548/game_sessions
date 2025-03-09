package com.mironov.sessions_app.service;

import com.mironov.sessions_app.entity.GameEntity;
import com.mironov.sessions_app.exception.GameNotFoundException;
import com.mironov.sessions_app.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameEntity getGameById(Long id){
        return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Игры не существует"));
    }
}
