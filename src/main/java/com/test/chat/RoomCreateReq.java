package com.test.chat;

import jakarta.validation.constraints.NotBlank;
public record RoomCreateReq(@NotBlank String name) {}