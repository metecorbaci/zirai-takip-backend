package com.ziraitakip.backend.util;

public class MessageBody{
    private String message;
    private String lang;
    public MessageBody(String message, String lang) {
        this.message = message;
        this.lang = lang;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}