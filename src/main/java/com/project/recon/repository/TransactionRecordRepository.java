package com.project.recon.repository;

import com.project.recon.entity.TransactionFileEntity;
import com.project.recon.entity.TransactionRecordEntity;

import java.util.List;

public interface TransactionRecordRepository
        extends JpaRepository<TransactionRecordEntity, Long> {

    List<TransactionRecordEntity> findByFile(TransactionFileEntity file);
    List<TransactionRecordEntity> findByFileId(Long fileId);

}
