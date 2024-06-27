package com.ziraitakip.backend.util;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseBody  {
    private Object message;
    private Object body;
    public ResponseBody(Object message, Object body) {
        this.message = message;
        this.body = body;
    }
    public static ResponseEntity send(Object message, Object data, HttpStatus status){
        return ResponseEntity.status(status).body(new ResponseBody(message, data));
    }
    public static ResponseEntity sendBody(ArrayList<MessageBody> messages, Object data, HttpStatus status){
        Map<String, String> messagesMap = new HashMap<>();
        for (MessageBody message : messages) {
            messagesMap.put(message.getLang(), message.getMessage());
        }
        return ResponseEntity.status(status).body(new ResponseBody(messagesMap, data));
    }
}
