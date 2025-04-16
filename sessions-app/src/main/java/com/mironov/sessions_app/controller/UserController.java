package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.DTO.UserDTO;
import com.mironov.sessions_app.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping("/set-username")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Установить никнейм пользователю")
    public ResponseEntity insertUsername(@RequestBody UserDTO userAuthData){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userAuthData.setEmail(authentication.getName());

        userService.updateUsername(userAuthData);

        return ResponseEntity.ok("username added");
    }
}
