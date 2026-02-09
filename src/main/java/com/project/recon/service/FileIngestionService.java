package com.project.recon.service;

import com.project.recon.entity.TransactionFileEntity;
import com.project.recon.exception.DuplicateFileException;
import com.project.recon.repository.TransactionFileRepository;
import com.project.recon.util.FileHashUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileIngestionService {

    private final TransactionFileRepository fileRepo;
    private final AuditService auditService;

    public FileIngestionService(TransactionFileRepository fileRepo,
                                AuditService auditService) {
        this.fileRepo = fileRepo;
        this.auditService = auditService;
    }

    @Transactional
    public TransactionFileEntity ingest(String fileName,
                                        List<String> lines) {

        String content = String.join("\n", lines);
        String hash = FileHashUtil.sha256(content);

        fileRepo.findByFileHash(hash)
                .ifPresent(f -> {
                    throw new DuplicateFileException(
                            "File already processed: " + fileName);
                });

        TransactionFileEntity file = new TransactionFileEntity();
        file.setFileName(fileName);
        file.setFileHash(hash);
        file.setStatus("RECEIVED");
        file.setUploadedAt(LocalDateTime.now());

        TransactionFileEntity saved = fileRepo.save(file);

        auditService.log(
                "TRANSACTION_FILE",
                saved.getId(),
                "CREATED",
                null,
                "RECEIVED"
        );

        return saved;
    }
}
