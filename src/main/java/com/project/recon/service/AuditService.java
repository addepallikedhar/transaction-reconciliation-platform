package com.project.recon.service;

import com.project.recon.entity.AuditLogEntity;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void log(
            String entityType,
            Long entityId,
            String action,
            String oldValue,
            String newValue) {

        AuditLogEntity audit = new AuditLogEntity();
        audit.setEntityType(entityType);
        audit.setEntityId(entityId);
        audit.setAction(action);
        audit.setOldValue(oldValue);
        audit.setNewValue(newValue);
        audit.setCreatedAt(LocalDateTime.now());

        auditLogRepository.save(audit);
    }
}
