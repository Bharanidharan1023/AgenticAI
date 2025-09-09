package com.agenticai.agentic_ai_demo_1.model;

public class ChatModel {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                "message='" + message + '\'' +
                '}';
    }
}
