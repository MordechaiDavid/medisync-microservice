package com.medisync.aiassistant.dto;

import com.medisync.aiassistant.entity.Chat;

public record ChatResponse(String message) {

    public static ChatResponse fromEntity(Chat entity) {
        return new ChatResponse(entity.getResponse());
    }
}