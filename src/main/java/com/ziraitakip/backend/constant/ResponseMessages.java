package com.ziraitakip.backend.constant;

import com.ziraitakip.backend.util.MessageBody;

import java.util.ArrayList;

public class ResponseMessages {
    public static ArrayList<MessageBody> LOGIN_SUCCESS(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Giriş başarılı.", "TR"));
        messages.add(new MessageBody("Login successful.", "EN"));
        return messages;
    }
    public static ArrayList<MessageBody> USER_NOT_FOUND(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Kullanıcı bulunamadı.", "TR"));
        messages.add(new MessageBody("User not found.", "EN"));
        return messages;
    }

    public static ArrayList<MessageBody> LOGIN_FAILED(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Geçersiz kullanıcı adı veya şifre.", "TR"));
        messages.add(new MessageBody("Invalid username or password.", "EN"));
        return messages;
    }

    public static ArrayList<MessageBody> REGISTER_SUCCESS(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Kayıt başarılı.", "TR"));
        messages.add(new MessageBody("Registration successful.", "EN"));
        return messages;
    }

    public static ArrayList<MessageBody> USER_ALREADY_EXISTS(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Kullanıcı zaten mevcut.", "TR"));
        messages.add(new MessageBody("User already exists.", "EN"));
        return messages;
    }

    public static ArrayList<MessageBody> USER_CONFIRMATION_DATA_NOT_FOUND(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Kullanıcı id ve aktivasyon kodu sağlanmalıdır.", "TR"));
        messages.add(new MessageBody("User id and activation code must be provided", "EN"));
        return messages;
    }
    public static ArrayList<MessageBody> USER_NOT_ACTIVATED(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Kullanıcı aktif değil.", "TR"));
        messages.add(new MessageBody("User not activated.", "EN"));
        return messages;
    }
    public static ArrayList<MessageBody> USER_ALREADY_ACTIVATED(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Kullanıcı zaten aktif.", "TR"));
        messages.add(new MessageBody("User already activated.", "EN"));
        return messages;
    }
    public static ArrayList<MessageBody> USER_ACTIVATION_CODE_NOT_MATCH(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Aktivasyon kodu eşleşmedi.", "TR"));
        messages.add(new MessageBody("Activation code not match.", "EN"));
        return messages;
    }
    public static ArrayList<MessageBody> USER_REGISTER_DATA_NOT_FOUND(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Kullanıcı bilgileri eksik.", "TR"));
        messages.add(new MessageBody("User information is missing.", "EN"));
        return messages;
    }


    public static ArrayList<MessageBody> USER_ACTIVATED(){
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(new MessageBody("Kullanıcı aktif edildi.", "TR"));
        messages.add(new MessageBody("User activated.", "EN"));
        return messages;
    }
}

