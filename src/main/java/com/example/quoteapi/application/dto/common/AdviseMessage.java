package com.example.quoteapi.application.dto.common;

public class AdviseMessage {
    public int code;
    public String message;
    
    public AdviseMessage(){}

    public AdviseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
