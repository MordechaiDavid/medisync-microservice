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

    private final RestTemplate restTemplate;
    private final ApiRequestBuilderFromLLM apiRequestBuilderFromLLM;
    private static final String API_GATEWAY_BASE_URL = "http://localhost:8080";

    public Chat generateApiRequestByUserMessage(String userMessage) {
        try {
            log.info("receive user message {}", userMessage);
            ApiRequest apiRequestToExecute = apiRequestBuilderFromLLM.extractApiRequest(userMessage);
            log.info("success receive from LLm");
            String fullUrl = API_GATEWAY_BASE_URL + apiRequestToExecute.endpoint();
            HttpMethod method = apiRequestToExecute.method();

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