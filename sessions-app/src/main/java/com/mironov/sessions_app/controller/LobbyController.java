package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.DTO.request.LobbyFilterRequest;
import com.mironov.sessions_app.DTO.response.FilteredLobbiesResponse;
import com.mironov.sessions_app.entity.LobbyEntity;
import com.mironov.sessions_app.entity.LobbyMemberEntity;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.service.GameService;
import com.mironov.sessions_app.service.LobbyMemberService;
import com.mironov.sessions_app.service.LobbyService;
import com.mironov.sessions_app.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")

@OpenAPIDefinition()
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

public class LobbyController {
    private final LobbyService lobbyService;
    private final UserService userService;
    private final LobbyMemberService lobbyMemberService;
    private final GameService gameService;

    public LobbyController(LobbyService lobbyService, UserService userService, LobbyMemberService lobbyMemberService, GameService gameService) {
        this.lobbyService = lobbyService;
        this.userService = userService;
        this.lobbyMemberService = lobbyMemberService;
        this.gameService = gameService;
    }

    @GetMapping("/get-lobby")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Получение информации о лобби")
    public ResponseEntity<LobbyEntity> getLobby(@RequestParam Long lobbyId){
        LobbyEntity responseEntity = lobbyService.getLobbyById(lobbyId);
        return ResponseEntity.ok(responseEntity);
    }

    //TODO
    @GetMapping("/lobby-filtered")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Получение лобби с фильтрами")
    public ResponseEntity<List<FilteredLobbiesResponse>> getFilteredLobbies(@RequestBody LobbyFilterRequest lobbyFilterRequest){
        return ResponseEntity.ok(lobbyService.getFilteredLobbies());
    }

    @PostMapping("/create-lobby")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Создать лобби")
    public ResponseEntity<LobbyEntity> createLobby(@RequestBody LobbyEntity lobby){
        LobbyEntity responseEntity = lobbyService.createLobby(lobby);
        return ResponseEntity.ok(responseEntity);
    }

    @PostMapping("/join-lobby")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Присоединится к лобби")
    public ResponseEntity joinLobby(@RequestParam Long lobbyId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LobbyEntity lobby = lobbyService.getLobbyById(lobbyId);
        UserEntity user = userService.loadUserByEmail(authentication.getName());


        if(lobbyMemberService.isUserInLobby(user,lobby))
            return ResponseEntity.badRequest().body("Такой пользватель уже в лобби");
        if(gameService.getGameById(lobby.getGameId()).getMaxLobbyCapacity() <= lobby.getCurrentCapacity()){
            return ResponseEntity.badRequest().body("Максимальное число пользователей в лобби");
        }

        lobbyService.currentCapacityIncrement(lobby);

        return lobbyMemberService.joinLobby(new LobbyMemberEntity(lobby, user, LocalDateTime.now().toString()));
    }

    @GetMapping("/get-lobby-members")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Все участники лобби")
    private ResponseEntity<List<UserEntity>> getAllMembers(Long lobbyId){
        LobbyEntity lobby = lobbyService.getLobbyById(lobbyId);
        return ResponseEntity.ok(lobbyMemberService.findAllUsersInLobby(lobby));
    }

    @DeleteMapping("/leave-lobby")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Покинуть лобби")
    private ResponseEntity leaveLobby(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.loadUserByEmail(authentication.getName());

        LobbyEntity lobby = lobbyMemberService.findLobbyByUser(user);
        lobbyService.currentCapacityDecrement(lobby);

        lobbyMemberService.deleteUserFromLobby(user);
        return ResponseEntity.ok("Пользователь удален");
    }

}
