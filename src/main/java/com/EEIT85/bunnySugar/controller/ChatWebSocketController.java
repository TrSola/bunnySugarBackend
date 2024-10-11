package com.EEIT85.bunnySugar.controller;

import com.EEIT85.bunnySugar.dto.ChatMessage; // 引用 DTO
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    // 儲存用戶的 WebSocket 會話
    private final Map<String, String> userSessions = new HashMap<>();

    public ChatWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send") // 當接收到 /app/send 的訊息時
    public void sendMessage(@Payload ChatMessage chatMessage) {
        // 根據用戶ID發送訊息
        String recipientId = chatMessage.getRecipientId(); // 從訊息中獲取接收者ID
        messagingTemplate.convertAndSendToUser(recipientId, "/topic/messages", chatMessage.getContent());
    }

    // 用於管理用戶會話
    public void registerUser(String userId, String sessionId) {
        userSessions.put(userId, sessionId);
    }

    public void unregisterUser(String userId) {
        userSessions.remove(userId);
    }
}
