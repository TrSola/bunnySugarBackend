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
    private ChatMessageRepository chatMessageRepository;

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final SimpMessagingTemplate messagingTemplate;

    public ChatService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 接收用戶傳過來的訊息
    public void receiveMessage(ChatMessageDto chatMessageDto) {
        Map<String, Object> claims = jwtUtil.parseJwtToken(chatMessageDto.getJwt());
        String senderId = claims.get("account").toString();
        logger.info("Received message: " + chatMessageDto.getContent() + " from account: " + senderId);

        // 將 DTO 轉換為 Entity
        ChatMessage messageEntity = new ChatMessage();
        messageEntity.setContent(chatMessageDto.getContent());
        messageEntity.setSenderId(senderId);
        messageEntity.setRecipientId("ADMIN");
        messageEntity.setTimestamp(LocalDateTime.now());

        // 儲存訊息到資料庫
        chatMessageRepository.save(messageEntity);

        // 回覆給客服
        sendMessageToAdmin(messageEntity);
    }

    // 傳送訊息給用戶
    public void sendMessageToUser(String content, String recipientId) {
        ChatMessage messageEntity = new ChatMessage();
        messageEntity.setContent(content);
        messageEntity.setSenderId("ADMIN");
        messageEntity.setRecipientId(recipientId);
        messageEntity.setTimestamp(LocalDateTime.now());

        // 儲存訊息到資料庫
        chatMessageRepository.save(messageEntity);

        // 發送訊息給用戶
        messagingTemplate.convertAndSendToUser(recipientId, "/topic/messages", messageEntity);
    }

    // 回覆訊息給客服
    private void sendMessageToAdmin(ChatMessage messageEntity) {
        messagingTemplate.convertAndSendToUser("ADMIN", "/topic/messages", messageEntity);
    }

}
