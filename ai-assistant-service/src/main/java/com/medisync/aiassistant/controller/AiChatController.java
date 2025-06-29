package com.medisync.aiassistant.controller;

import com.medisync.aiassistant.dto.ChatRequest;
import com.medisync.aiassistant.dto.ChatResponse;
import com.medisync.aiassistant.entity.Chat;
import com.medisync.aiassistant.service.AiChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiChatController {
    
    private final AiChatService aiChatService;
    
    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        Chat chat = aiChatService.generateApiRequest(request.message());
        return ResponseEntity.ok(ChatResponse.fromEntity(chat));
    }

}