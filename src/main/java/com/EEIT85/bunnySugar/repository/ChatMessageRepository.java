package com.EEIT85.bunnySugar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.EEIT85.bunnySugar.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    // 可以根據需要添加查詢方法
}
