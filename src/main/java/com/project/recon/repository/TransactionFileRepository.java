package com.project.recon.repository;

import com.project.recon.entity.TransactionFileEntity;

import java.util.Optional;

public interface TransactionFileRepository
        extends JpaRepository<TransactionFileEntity, Long> {

    Optional<TransactionFileEntity> findByFileHash(String fileHash);
}
