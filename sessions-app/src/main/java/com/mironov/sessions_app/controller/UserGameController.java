package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.DTO.request.UpdateUserGamesRequest;
import com.mironov.sessions_app.service.UserGameService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-games")
public class UserGameController {

    private final UserGameService userGameService;

    public UserGameController(UserGameService userGameService) {
        this.userGameService = userGameService;
    }


    @PutMapping("/add-or-update")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Добавить игры и ранги пользователю")
    public ResponseEntity<String> addGames(@RequestBody UpdateUserGamesRequest userGamesRequest){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usersEmail = authentication.getName();

        if(!userGamesRequest.getGameRankList().isEmpty())
            return userGameService.insertUserGames(usersEmail, userGamesRequest.getGameRankList());
        else
            return ResponseEntity.badRequest().body("Список пуст. Добавлять нечего");

    }

    @GetMapping("/get-user-games")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Получить игры и ранги пользователя")
    public ResponseEntity getUserGames(@RequestParam Long userId){
        return ResponseEntity.ok(userGameService.getUserGamesById(userId));
    }
}
