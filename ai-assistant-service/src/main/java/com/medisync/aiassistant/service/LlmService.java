package com.medisync.aiassistant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisync.aiassistant.entity.Intent;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LlmService {

    private final ChatLanguageModel chatLanguageModel;
    private final ObjectMapper objectMapper;

    private static final String SYSTEM_PROMPT = """
            You are an intelligent API router for a healthcare management system called medisync.
            
            Your job is to analyze user requests and return ONLY a JSON response with this exact structure:
            {
              "intent": "<INTENT_NAME>",
              "parameters": {
                "id": "<optional_id_if_mentioned>"
              }
            }
            
            Available intents:
            - GET_ALL_USERS: Show all users, list users
            - GET_USER_BY_ID: Get specific user by ID
            - GET_ALL_PATIENTS: Show all patients, list patients
            - GET_PATIENT_BY_ID: Get specific patient by ID
            - GET_ALL_DOCTORS: Show all doctors, list doctors
            - GET_DOCTOR_BY_ID: Get specific doctor by ID
            - GET_ALL_APPOINTMENTS: Show all appointments, list appointments
            - GET_APPOINTMENT_BY_ID: Get specific appointment by ID
            - UNKNOWN: For requests that don't match any intent
            
            Examples:
            User: "Show me all patients" → {"intent": "GET_ALL_PATIENTS", "parameters": {}}
            User: "Get user with ID 5" → {"intent": "GET_USER_BY_ID", "parameters": {"id": "5"}}
            User: "List all doctors" → {"intent": "GET_ALL_DOCTORS", "parameters": {}}
            
            Return ONLY the JSON, ONLY from intents. no explanations.
            """;

    public IntentResult extractIntent(String userMessage) {
        try {
            log.debug("Processing user message: {}", userMessage);
            
            String prompt = SYSTEM_PROMPT + "\n\nUser message: " + userMessage;
            String response = chatLanguageModel.generate(prompt);
            
            log.debug("LLM response: {}", response);
            
            // Parse JSON response
            JsonNode jsonNode = objectMapper.readTree(response);
            String intentStr = jsonNode.get("intent").asText();
            Intent intent = Intent.valueOf(intentStr);
            
            // Extract parameters
            String id = null;
            if (jsonNode.has("parameters") && jsonNode.get("parameters").has("id")) {
                id = jsonNode.get("parameters").get("id").asText();
            }
            
            log.debug("Extracted intent: {}, id: {}", intent, id);
            return new IntentResult(intent, id);
            
        } catch (Exception e) {
            log.error("Error extracting intent from message: {}", userMessage, e);
            // If anything goes wrong, return UNKNOWN intent
            return new IntentResult(Intent.UNKNOWN, null);
        }
    }

    public record IntentResult(Intent intent, String id) {}
}