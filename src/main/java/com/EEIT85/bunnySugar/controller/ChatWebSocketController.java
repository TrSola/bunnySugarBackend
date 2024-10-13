package com.EEIT85.bunnySugar.controller;

import com.EEIT85.bunnySugar.dto.ChatMessageDto;
import com.EEIT85.bunnySugar.service.ChatService; // 引用 Service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class ChatWebSocketController {

    @Autowired
    private ChatService chatService; // 注入 Service

    @MessageMapping("/send") // 當接收到 /app/send 的訊息時
    public void sendMessage(@Payload ChatMessageDto chatMessage) {
        chatService.receiveMessage(chatMessage); // 將訊息發送到 Service 處理
    }

    // 用戶註冊和取消註冊的邏輯可以考慮放到 Service 中
}
