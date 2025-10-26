package com.proy.backend_donaciones.domain.dto;

public class LoginRequest {
    private String email;
    private String password;

    //<editor-fold desc="Getters and Setters">
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>
}