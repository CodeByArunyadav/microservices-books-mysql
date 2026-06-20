package com.example.audit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String username;
    private String serviceName;
    private String operation;
    private String resource;
    private String resourceId;
    private String ipAddress;

    @Column(length = 1000)
    private String message;

    private LocalDateTime timestamp;
}
