package com.project.recon.repository;

import com.project.recon.entity.ReconciliationResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReconciliationResultRepository
        extends JpaRepository<ReconciliationResultEntity, Long> {
}
