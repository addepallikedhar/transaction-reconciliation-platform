package com.project.recon.service;

import com.project.recon.entity.AuditLogEntity;
import com.project.recon.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final AuditLogRepository auditRepo;

    public AuditService(AuditLogRepository auditRepo) {
        this.auditRepo = auditRepo;
    }

    public void log(String entityType,
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

        auditRepo.save(audit);
    }
}
