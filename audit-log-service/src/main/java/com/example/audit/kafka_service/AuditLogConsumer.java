package com.example.audit.kafka_service; 
import com.example.audit.dto.AuditEvent;
import com.example.audit.entity.AuditLog;
import com.example.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogConsumer {

    private final AuditLogRepository auditLogRepository;

    @KafkaListener(topics = "cloud-audit-log", groupId = "audit-log-group")
    public void consume(AuditEvent event) {

        AuditLog log = AuditLog.builder()
                .userId(event.getUserId())
                .username(event.getUsername())
                .serviceName(event.getServiceName())
                .operation(event.getOperation())
                .resource(event.getResource())
                .resourceId(event.getResourceId())
                .message(event.getMessage())
                .ipAddress(event.getIpAddress())
                .timestamp(event.getTimestamp())
                .build();

        auditLogRepository.save(log);
    }
}