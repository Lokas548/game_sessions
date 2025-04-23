package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.DTO.UserDTO;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-nickname")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Получить никнейм пользователя")
    public ResponseEntity getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.loadUserByEmail(authentication.getName());
        if(user.getUsername() == null || user.getUsername().isEmpty()){
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping("/set-username")
    @SecurityRequirement(name = "JWT")
    @Tag(name = "Установить никнейм пользователю")
    public ResponseEntity insertUsername(@RequestBody UserDTO userAuthData){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info( authentication.getDetails().toString());

        userAuthData.setEmail(authentication.getName());

        userService.updateUsername(userAuthData);

        return ResponseEntity.ok("username added");
    }
}
