package com.project.recon.repository;

import com.project.recon.entity.AuditLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository
        extends JpaRepository<AuditLogEntity, Long> {
}
