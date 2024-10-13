package com.EEIT85.bunnySugar.service;

import com.EEIT85.bunnySugar.dto.ChatMessageDto; // 引入 DTO
import com.EEIT85.bunnySugar.entity.ChatMessage;
import com.EEIT85.bunnySugar.repository.ChatMessageRepository; // 引入 Repository
import com.EEIT85.bunnySugar.utils.JwtUtil; // 引入 JWT 工具
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ChatService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ChatMessageRepository chatMessageRepository; // 引入 repository

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final SimpMessagingTemplate messagingTemplate;

    public ChatService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 接收用戶傳過來的訊息
    public void receiveMessage(ChatMessageDto chatMessageDto) {
        // 打印接收到的訊息到控制台
        Map<String, Object> claims = jwtUtil.parseJwtToken(chatMessageDto.getSenderId());
        String senderId = claims.get("account").toString(); // 獲取用戶的帳號
        logger.info("Received message: " + chatMessageDto.getContent() + " from account: " + senderId);

        // 將 DTO 轉換為 Entity
        ChatMessage messageEntity = new ChatMessage();
        messageEntity.setContent(chatMessageDto.getContent());
        messageEntity.setSenderId(senderId); // 設置發送者ID
        messageEntity.setRecipientId("ADMIN"); // 設置接收者ID為 ADMIN
        messageEntity.setTimestamp(LocalDateTime.now()); // 設定當前時間戳

        // 儲存訊息到資料庫
        chatMessageRepository.save(messageEntity); // 儲存到資料表

        // 回覆給客服
        sendMessageToAdmin(messageEntity);

        // 發送訊息給特定用戶
        String recipientId = "istoo1028"; // 目標用戶的 ID
        String messageContent = "您好，這是客服發送的測試訊息。"; // 要發送的訊息內容

        ChatMessage messageEntity22 = new ChatMessage();
        messageEntity.setContent(messageContent);
        messageEntity.setSenderId("ADMIN"); // 設置發送者ID為客服
        messageEntity.setRecipientId(recipientId); // 設置接收者ID
        messageEntity.setTimestamp(LocalDateTime.now()); // 設定當前時間戳

// 發送訊息到特定用戶
        messagingTemplate.convertAndSendToUser(recipientId, "/topic/messages", messageEntity22);
    }

    // 傳送訊息給用戶
    public void sendMessageToUser(String recipientId, String content) {
        ChatMessage messageEntity = new ChatMessage();
        messageEntity.setContent(content);
        messageEntity.setSenderId("ADMIN"); // 設置客服的ID
        messageEntity.setRecipientId(recipientId); // 設置用戶ID
        messageEntity.setTimestamp(LocalDateTime.now()); // 設定當前時間戳

        // 儲存訊息到資料庫
        chatMessageRepository.save(messageEntity); // 儲存到資料表

        // 發送訊息給用戶
        messagingTemplate.convertAndSendToUser(recipientId, "/topic/messages", messageEntity);
    }

    // 回覆訊息給客服
    private void sendMessageToAdmin(ChatMessage messageEntity) {
        messagingTemplate.convertAndSendToUser("ADMIN", "/topic/messages", messageEntity);
    }
}
