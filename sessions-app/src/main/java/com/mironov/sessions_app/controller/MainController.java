package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.service.UserService;
import com.mironov.sessions_app.util.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public MainController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/test")
    public ResponseMessage testMessage(){
        System.out.println("get");
        return new ResponseMessage("hello world!");
    }

    @GetMapping("/decrypt-token/")
    public String decryption(@RequestParam String token){
        return jwtUtil.extractUsername(token);
    }


    public static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
