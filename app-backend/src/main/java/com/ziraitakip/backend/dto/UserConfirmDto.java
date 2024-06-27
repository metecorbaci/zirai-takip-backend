package com.ziraitakip.backend.dto;

public class UserConfirmDto {
    public static String USER_ID = "user_id";
    public static String ACTIVATION_CODE = "activation_code";
    private Integer user_id;
    private String activation_code;
    private String token;

    public UserConfirmDto(Integer user_id, String activation_code) {
        this.user_id = user_id;
        this.activation_code = activation_code;
    }

    public UserConfirmDto(Integer user_id, String activation_code,String token) {
        this.user_id = user_id;
        this.activation_code = activation_code;
        this.token = token;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
