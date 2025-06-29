package com.medisync.aiassistant.client;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeminiApiClient {
    private final Client client;

    public GeminiApiClient(@Value("${gamini.api.key}") String apiKey) {
        this.client = Client.builder().apiKey(apiKey).build();
    }

    public GenerateContentResponse generateContentResponse(String model, String userMessage) {
        return this.client.models.generateContent(
                model, userMessage, null
        );
    }



}
