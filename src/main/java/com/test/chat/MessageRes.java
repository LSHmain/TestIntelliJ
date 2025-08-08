package com.test.chat;

import java.time.LocalDateTime;

public record MessageRes(
        Long id, Long roomId, String senderId, String content,
        String messageType, LocalDateTime createdAt
) {}