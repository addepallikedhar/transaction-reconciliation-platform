package com.project.recon.engine;

import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ReconciliationEngineTest {

    private ReconciliationEngine engine;
    private ReconciliationContext context;

    @Before
    public void setup() {
        engine = new ReconciliationEngine();
        context = new ReconciliationContext();
    }

    @Test
    public void matchedTransaction() {
        TransactionRecordEntity record =
                record("REF1", LocalDate.now());

        ReconciliationResultEntity result =
                engine.reconcile(record, context);

        assertEquals("MATCHED", result.getResultCode());
    }

    @Test
    public void duplicateTransaction() {
        TransactionRecordEntity r1 = record("REF1", LocalDate.now());
        TransactionRecordEntity r2 = record("REF1", LocalDate.now());

        engine.reconcile(r1, context);
        ReconciliationResultEntity result =
                engine.reconcile(r2, context);

        assertEquals("DUPLICATE", result.getResultCode());
    }

    @Test
    public void invalidFutureDate() {
        TransactionRecordEntity record =
                record("REF2", LocalDate.now().plusDays(1));

        ReconciliationResultEntity result =
                engine.reconcile(record, context);

        assertEquals("INVALID_DATE", result.getResultCode());
    }

    private TransactionRecordEntity record(String ref, LocalDate date) {
        TransactionRecordEntity r = new TransactionRecordEntity();
        r.setReferenceId(ref);
        r.setAmount(BigDecimal.TEN);
        r.setBusinessDate(date);
        return r;
    }
}
