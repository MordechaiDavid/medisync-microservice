package com.medisync.aiassistant.service;

import com.medisync.aiassistant.dto.ApiRequest;
import com.medisync.aiassistant.entity.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiChatService {
    private final LlmService llmService;
    private final RestTemplate restTemplate;
    private static final String API_GATEWAY_BASE_URL = "http://localhost:8080";

    public Chat generateApiRequest(String userMessage) {
        try {
            ApiRequest apiRequest = llmService.extractApiRequest(userMessage);
            String fullUrl = API_GATEWAY_BASE_URL + apiRequest.endpoint();
            HttpMethod method = apiRequest.method();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> apiResponse = restTemplate.exchange(
                    fullUrl, method, httpEntity, String.class
            );
            return new Chat(userMessage, apiResponse.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}