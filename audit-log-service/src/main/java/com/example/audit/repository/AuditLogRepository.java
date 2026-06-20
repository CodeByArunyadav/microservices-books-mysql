package com.example.audit.repository;

import com.example.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByUserId(String userId);

    List<AuditLog> findByServiceName(String serviceName);

    List<AuditLog> findByOperation(String operation);
}