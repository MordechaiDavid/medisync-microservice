package com.medisync.aiassistant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisync.aiassistant.client.GeminiApiClient;
import com.medisync.aiassistant.dto.ApiRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApiRequestBuilderFromLLM {

    private final ObjectMapper objectMapper;
    private final GeminiApiClient geminiApiClient;
    private static final String SYSTEM_PROMPT = """
            You are an API router for a healthcare system.
            
            Convert user requests directly to API endpoints. Return ONLY a JSON response:
            {
              "endpoint": "<API_ENDPOINT>",
              "method": "<HTTP_METHOD>"
            }
            
            Available endpoints:
            - GET /api/users - Show all users
            - GET /api/users/{id} - Get specific user by ID
            - GET /api/patients - Show all patients
            - GET /api/patients/{id} - Get specific patient by ID
            - GET /api/doctors - Show all doctors
            - GET /api/doctors/{id} - Get specific doctor by ID
            - GET /api/appointments - Show all appointments
            - GET /api/appointments/{id} - Get specific appointment by ID
            
            Examples:
            User: "Show me all patients" → {"endpoint": "/api/patients", "method": "GET"}
            User: "Get user with ID 5" → {"endpoint": "/api/users/5", "method": "GET"}
            User: "List all doctors" → {"endpoint": "/api/doctors", "method": "GET"}
            
            Return ONLY the JSON, no explanations.
            """;

    public ApiRequestBuilderFromLLM(ObjectMapper objectMapper, GeminiApiClient geminiApiClient) {
        this.objectMapper = objectMapper;
        this.geminiApiClient = geminiApiClient;
    }

    public ApiRequest extractApiRequest(String userMessage) {
        try {
            String prompt = SYSTEM_PROMPT + "\n\n User message: " + userMessage;
            log.info("send prompt to gamma model: {}", prompt);
            String response = geminiApiClient.generateContentResponse("gemma-3n-e2b-it", prompt).text();
            log.info("LLM response: {}", response);
            String cleanResponse = cleanJsonResponse(response);
            log.info("clean response: {}", cleanResponse);

            JsonNode jsonNode = objectMapper.readTree(cleanResponse);
            String endpoint = jsonNode.get("endpoint").asText();
            String methodStr = jsonNode.get("method").asText();
            HttpMethod method = HttpMethod.valueOf(methodStr);

            return new ApiRequest(endpoint, method);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String cleanJsonResponse(String rawResponse) {
        if (rawResponse == null) return "";

        String trimmed = rawResponse.trim();
        if (trimmed.startsWith("```")) {
            trimmed = trimmed.replaceAll("(?s)^```[a-zA-Z]*\\n", "");
            trimmed = trimmed.replaceAll("\\n```$", "");
        }

        return trimmed;
    }
}