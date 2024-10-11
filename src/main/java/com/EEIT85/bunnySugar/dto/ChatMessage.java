package com.EEIT85.bunnySugar.dto;

public class ChatMessage {
    private String content;
    private String recipientId;

    // 無參數構造函數
    public ChatMessage() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }
}
