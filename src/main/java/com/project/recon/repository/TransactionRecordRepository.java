package com.project.recon.repository;

import com.project.recon.entity.TransactionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRecordRepository
        extends JpaRepository<TransactionRecordEntity, Long> {

    List<TransactionRecordEntity> findByFileId(Long fileId);
}
