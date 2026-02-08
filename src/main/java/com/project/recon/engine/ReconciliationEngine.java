package com.project.recon.engine;

import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ReconciliationEngine {

    private static final BigDecimal AMOUNT_TOLERANCE = new BigDecimal("0.50");

    public ReconciliationResultEntity reconcile(
            TransactionRecordEntity record,
            ReconciliationContext context) {

        ReconciliationResultEntity result = new ReconciliationResultEntity();
        result.setTransaction(record);
        result.setReconciledAt(LocalDateTime.now());

        // Rule 1: Duplicate check
        if (context.isDuplicate(record.getReferenceId())) {
            result.setResultCode(ReconciliationResultCode.DUPLICATE.name());
            result.setRemarks("Duplicate reference in same file");
            return result;
        }

        // Rule 2: Business date validation
        if (record.getBusinessDate().isAfter(LocalDate.now())) {
            result.setResultCode(ReconciliationResultCode.INVALID_DATE.name());
            result.setRemarks("Business date is in the future");
            return result;
        }

        // Rule 3: Amount tolerance check
        BigDecimal expectedAmount = record.getAmount(); // placeholder for reference lookup
        BigDecimal diff = record.getAmount().subtract(expectedAmount).abs();

        if (diff.compareTo(AMOUNT_TOLERANCE) <= 0) {
            result.setResultCode(ReconciliationResultCode.MATCHED.name());
            result.setRemarks("Transaction matched successfully");
        } else {
            result.setResultCode(ReconciliationResultCode.AMOUNT_MISMATCH.name());
            result.setRemarks("Amount mismatch beyond tolerance");
        }

        return result;
    }
}
