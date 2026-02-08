package com.project.recon.service;

import com.project.recon.entity.TransactionFileEntity;
import com.project.recon.exception.DuplicateFileException;
import com.project.recon.repository.TransactionFileRepository;
import com.project.recon.util.FileHashUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileIngestionService {

    private final TransactionFileRepository fileRepository;
    private final AuditService auditService;

    @Transactional
    public TransactionFileEntity ingestFile(
            String fileName,
            List<String> csvLines) {

        String content = String.join("\n", csvLines);
        String fileHash = FileHashUtil.sha256(content);

        fileRepository.findByFileHash(fileHash)
                .ifPresent(f ->
                { throw new DuplicateFileException(
                        "File already processed: " + fileName); });

        TransactionFileEntity file = new TransactionFileEntity();
        file.setFileName(fileName);
        file.setFileHash(fileHash);
        file.setStatus("RECEIVED");
        file.setUploadedAt(LocalDateTime.now());

        TransactionFileEntity saved = fileRepository.save(file);

        auditService.log(
                "TRANSACTION_FILE",
                saved.getId(),
                "CREATED",
                null,
                "RECEIVED");

        return saved;
    }
}
