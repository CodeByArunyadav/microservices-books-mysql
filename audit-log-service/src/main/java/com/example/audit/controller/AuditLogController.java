package com.example.audit.controller; 
import com.example.audit.entity.AuditLog;
import com.example.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogRepository auditLogRepository;

    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<AuditLog> getByUser(@PathVariable String userId) {
        return auditLogRepository.findByUserId(userId);
    }

    @GetMapping("/service/{serviceName}")
    public List<AuditLog> getByService(@PathVariable String serviceName) {
        return auditLogRepository.findByServiceName(serviceName);
    }

    @GetMapping("/operation/{operation}")
    public List<AuditLog> getByOperation(@PathVariable String operation) {
        return auditLogRepository.findByOperation(operation);
    }
}
