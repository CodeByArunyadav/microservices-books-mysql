package com.codebyarunyadav.book.kafka; 
import com.codebyarunyadav.book.DTO.AuditEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditProducer {

    private static final String TOPIC = "cloud-audit-log";

    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;

    public void sendAuditLog(AuditEvent event) {
        kafkaTemplate.send(TOPIC, event.getUserId(), event);
    }
}
