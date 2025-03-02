package com.mironov.sessions_app.controller;

import com.mironov.sessions_app.DTO.UserDTO;
import com.mironov.sessions_app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseMessage testMessage(){
        System.out.println("get");
        return new ResponseMessage("hello world!");
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
