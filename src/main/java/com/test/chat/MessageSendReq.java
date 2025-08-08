package com.test.chat;

import jakarta.validation.constraints.NotBlank;
public record MessageSendReq(
        @NotBlank String senderId,
        @NotBlank String content,
        String messageType // null이면 text
) {}