package com.medisync.aiassistant.dto;

import org.springframework.http.HttpMethod;

public record ApiRequest(String endpoint, HttpMethod method) {}