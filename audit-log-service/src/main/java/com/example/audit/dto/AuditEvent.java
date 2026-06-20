package com.example.audit.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditEvent {

    private String userId;
    private String username;
    private String serviceName;
    private String operation;
    private String resource;
    private String resourceId;
    private String message;
    private String ipAddress;
    private LocalDateTime timestamp;
}