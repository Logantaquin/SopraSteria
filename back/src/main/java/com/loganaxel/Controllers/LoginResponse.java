package com.loganaxel.Controllers;

public class LoginResponse {
    private String userId;
    private String equipeId;
    private Boolean isAdmin;

    // Constructeur
    public LoginResponse(String userId, String equipeId, Boolean admin) {
        this.userId = userId;
        this.equipeId = equipeId;
        this.isAdmin = admin;
    }

    // Getters et setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(String equipeId) {
        this.equipeId = equipeId;
    }

    public boolean getAdmin() {
        return isAdmin;
    }
}

