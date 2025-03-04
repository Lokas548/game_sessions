package com.mironov.sessions_app.DTO.response;

import org.springframework.stereotype.Component;

@Component
public class AuthTokenResponse {
    private String jwtToken;

    public AuthTokenResponse() {
    }

    public AuthTokenResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
