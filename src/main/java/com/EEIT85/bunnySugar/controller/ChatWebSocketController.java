package com.EEIT85.bunnySugar.controller;

import com.EEIT85.bunnySugar.dto.ChatMessageDto;
import com.EEIT85.bunnySugar.entity.ChatMessage;
import com.EEIT85.bunnySugar.service.ChatService; // 引用 Service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin
public class ChatWebSocketController {

    @Autowired
    private ChatService chatService; // 注入 Service

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send") // 當接收到 /app/send 的訊息時
    public void sendMessage(@Payload ChatMessageDto chatMessage) {
        chatService.receiveMessage(chatMessage); // 將訊息發送到 Service 處理
    }

    @GetMapping("/send-message")
    public ResponseEntity<Void> sendMessage(@RequestParam String content, @RequestParam String recipientId) {
        // 創建訊息對象
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(content);
        chatMessage.setRecipientId(recipientId);
        chatMessage.setSenderId("ADMIN"); // 設定發送者為 ADMIN
        chatMessage.setTimestamp(LocalDateTime.now()); // 當前時間戳

        // 使用 STOMP 將訊息發送到特定用戶
        messagingTemplate.convertAndSend("/user/" + recipientId + "/topic/messages", chatMessage);
        return ResponseEntity.ok().build(); // 返回成功響應
    }
}
