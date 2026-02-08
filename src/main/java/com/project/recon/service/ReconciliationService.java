package com.project.recon.service;

import com.project.recon.engine.ReconciliationContext;
import com.project.recon.engine.ReconciliationEngine;
import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionFileEntity;
import com.project.recon.entity.TransactionRecordEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReconciliationService {

    private final TransactionRecordRepository recordRepository;
    private final ReconciliationResultRepository resultRepository;
    private final ReconciliationEngine engine;

    @Transactional
    public void reconcileFile(TransactionFileEntity file) {

        List<TransactionRecordEntity> records =
                recordRepository.findByFile(file);

        ReconciliationContext context = new ReconciliationContext();

        for (TransactionRecordEntity record : records) {
            ReconciliationResultEntity result =
                    engine.reconcile(record, context);

            record.setStatus(result.getResultCode());

            resultRepository.save(result);
            recordRepository.save(record);
        }
    }

    @Transactional
    public void reconcileFile(TransactionFileEntity file) {

        auditService.log(
                "TRANSACTION_FILE",
                file.getId(),
                "STATUS_CHANGE",
                file.getStatus(),
                "PROCESSING");

        file.setStatus("PROCESSING");

        List<TransactionRecordEntity> records =
                recordRepository.findByFile(file);

        ReconciliationContext context = new ReconciliationContext();

        for (TransactionRecordEntity record : records) {

            ReconciliationResultEntity result =
                    engine.reconcile(record, context);

            record.setStatus(result.getResultCode());

            resultRepository.save(result);
            recordRepository.save(record);
        }

        auditService.log(
                "TRANSACTION_FILE",
                file.getId(),
                "STATUS_CHANGE",
                "PROCESSING",
                "PROCESSED");

        file.setStatus("PROCESSED");
    }

}

