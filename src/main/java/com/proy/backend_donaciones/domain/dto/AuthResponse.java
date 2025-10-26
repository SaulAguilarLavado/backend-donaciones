package com.proy.backend_donaciones.domain.dto;

public class AuthResponse {

    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    //<editor-fold desc="Getters and Setters">
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    //</editor-fold>
}