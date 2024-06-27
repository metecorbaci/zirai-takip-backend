package com.ziraitakip.backend.dto;

public class LoginResDto {
    private LoggedUserDto user;
    private String token;

    public LoginResDto() {
    }

    public LoginResDto(LoggedUserDto user, String token) {
        this.user = user;
        this.token = token;
    }

    public LoggedUserDto getUser() {
        return user;
    }

    public void setUser(LoggedUserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
