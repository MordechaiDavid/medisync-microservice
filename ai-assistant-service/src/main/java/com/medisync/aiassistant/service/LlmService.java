package com.medisync.aiassistant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisync.aiassistant.dto.ApiRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LlmService {

//    private final ObjectMapper objectMapper;
//
//    private static final String SYSTEM_PROMPT = """
//            You are an API router for a healthcare system.
//
//            Convert user requests directly to API endpoints. Return ONLY a JSON response:
//            {
//              "endpoint": "<API_ENDPOINT>",
//              "method": "<HTTP_METHOD>"
//            }
//
//            Available endpoints:
//            - GET /api/users - Show all users
//            - GET /api/users/{id} - Get specific user by ID
//            - GET /api/patients - Show all patients
//            - GET /api/patients/{id} - Get specific patient by ID
//            - GET /api/doctors - Show all doctors
//            - GET /api/doctors/{id} - Get specific doctor by ID
//            - GET /api/appointments - Show all appointments
//            - GET /api/appointments/{id} - Get specific appointment by ID
//
//            Examples:
//            User: "Show me all patients" → {"endpoint": "/api/patients", "method": "GET"}
//            User: "Get user with ID 5" → {"endpoint": "/api/users/5", "method": "GET"}
//            User: "List all doctors" → {"endpoint": "/api/doctors", "method": "GET"}
//
//            Return ONLY the JSON, no explanations.
//            """;
//
//    public ApiRequest extractApiRequest(String userMessage) {
//        try {
//            String prompt = SYSTEM_PROMPT + "\n\nUser message: " + userMessage;
////            String response = chatLanguageModel.generate(prompt);
////            log.info("LLM response: {}", response);
////
////            JsonNode jsonNode = objectMapper.readTree(response);
////            String endpoint = jsonNode.get("endpoint").asText();
////            String methodStr = jsonNode.get("method").asText();
//            HttpMethod method = HttpMethod.valueOf(methodStr);
//
//            return new ApiRequest(endpoint, method);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}