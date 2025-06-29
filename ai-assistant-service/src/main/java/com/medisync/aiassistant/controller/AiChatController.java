package com.medisync.aiassistant.controller;

import com.medisync.aiassistant.client.GeminiApiClient;
import com.medisync.aiassistant.dto.ChatRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiChatController {
    
    private final GeminiApiClient geminiApiClient;
//
//    @PostMapping("/chat")
//    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
//        Chat chat = aiChatService.generateApiRequest(request.message());
//        return ResponseEntity.ok(ChatResponse.fromEntity(chat));
//    }

    @GetMapping("/test")
    public String testGamini(){
        return geminiApiClient.generateContentResponse(
                "gemma-3-1b-it", "give me some good news").text();
    }

}