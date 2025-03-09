package com.mironov.sessions_app.service;


import com.mironov.sessions_app.DTO.request.UpdateUserGamesRequest;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.entity.UserGameEntity;
import com.mironov.sessions_app.repository.UserGameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGameService {

    private final UserService userService;
    private final UserGameRepository userGameRepository;
    private final GameService gameService;

    public UserGameService(UserService userService, UserGameRepository userGameRepository, GameService gameService) {
        this.userService = userService;
        this.userGameRepository = userGameRepository;
        this.gameService = gameService;
    }

    public ResponseEntity<String> insertUserGames(String email, List<UpdateUserGamesRequest.GameRank> list){
        UserEntity user = userService.loadUserByEmail(email);

        for(UpdateUserGamesRequest.GameRank game : list){
            UserGameEntity userGameEntity = new UserGameEntity(game.rank,gameService.getGameById(game.gameId),user);
            userGameRepository.save(userGameEntity);
        }

        return ResponseEntity.ok().body("Игры добавленны");
    }

    public List<UserGameEntity> getUserGamesById(Long userId){
        return userGameRepository.findByUser(userService.loadUserById(userId));
    }
}
