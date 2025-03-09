package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.entity.LobbyEntity;
import com.mironov.sessions_app.service.LobbyService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @GetMapping("/lobby")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Получение информации о лобби")
    public ResponseEntity<LobbyEntity> getLobby(@RequestParam Long lobbyId){
        LobbyEntity responseEntity = lobbyService.getLobbyById(lobbyId);
        return ResponseEntity.ok(responseEntity);
    }

    @PostMapping("/lobby")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Создать лобби")
    public ResponseEntity<LobbyEntity> createLobby(@RequestBody LobbyEntity lobby){
        LobbyEntity responseEntity = lobbyService.createLobby(lobby);
        return ResponseEntity.ok(responseEntity);
    }

}
