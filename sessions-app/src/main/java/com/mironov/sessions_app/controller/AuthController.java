package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.DTO.UserDTO;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.service.UserService;
import com.mironov.sessions_app.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userAuthData){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthData.getEmail(),userAuthData.getPassword()));

        final UserEntity user = userService.loadUserByEmail(userAuthData.getEmail());
        String jwt = jwtUtil.generateToken(user.getEmail(), user.getId());
        return ResponseEntity.ok(jwt);
    }


    @PostMapping("/registration")
    public ResponseEntity<Void> registration(@RequestBody UserDTO userRegistrationData) {
        userService.saveUser(userRegistrationData);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}