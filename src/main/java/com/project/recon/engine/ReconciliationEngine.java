package com.project.recon.engine;

import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ReconciliationEngine {

    public ReconciliationResultEntity reconcile(
            TransactionRecordEntity record,
            ReconciliationContext context) {

        ReconciliationResultEntity result = new ReconciliationResultEntity();
        result.setTransaction(record);
        result.setReconciledAt(LocalDateTime.now());

        if (context.isDuplicate(record.getReferenceId())) {
            result.setResultCode(ReconciliationResultCode.DUPLICATE.name());
            result.setRemarks("Duplicate reference in same file");
            return result;
        }

        if (record.getBusinessDate().isAfter(LocalDate.now())) {
            result.setResultCode(ReconciliationResultCode.INVALID_DATE.name());
            result.setRemarks("Future business date");
            return result;
        }

        result.setResultCode(ReconciliationResultCode.MATCHED.name());
        result.setRemarks("Matched successfully");
        return result;
    }
}
