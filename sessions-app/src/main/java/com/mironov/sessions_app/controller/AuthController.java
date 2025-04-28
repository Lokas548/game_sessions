package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.DTO.AuthDTO;
import com.mironov.sessions_app.DTO.response.AuthTokenResponse;
import com.mironov.sessions_app.entity.UserEntity;
import com.mironov.sessions_app.service.UserService;
import com.mironov.sessions_app.util.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    @Tag(name = "Авторизация")
    public ResponseEntity login(@RequestBody AuthDTO userAuthData){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthData.getEmail(), userAuthData.getPassword()));

            final UserEntity userEntity = userService.loadUserByEmail(userAuthData.getEmail());
            final UserDetails user = userDetailsService.loadUserByUsername(userAuthData.getEmail());

            return ResponseEntity.ok(new AuthTokenResponse(jwtUtil.generateToken(user.getUsername(), userEntity.getId())));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверные учетные данные");
        }
    }


    @PostMapping("/registration")
    @Tag(name = "Регистрация")
    public String registration(@RequestBody AuthDTO userRegistrationData) {
        userService.saveUser(userRegistrationData);
        return "200";
    }
}