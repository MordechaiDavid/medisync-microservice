package com.medisync.aiassistant.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Chat {
    private String request;
    private String response;
}