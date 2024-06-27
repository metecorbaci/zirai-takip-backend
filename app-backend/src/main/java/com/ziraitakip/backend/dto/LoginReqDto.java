package com.ziraitakip.backend.dto;

public class LoginReqDto {
    private String email;
    private String password;

    public LoginReqDto() {
    }

    public LoginReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
