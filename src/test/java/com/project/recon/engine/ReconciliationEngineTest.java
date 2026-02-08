package com.project.recon.engine;

public class ReconciliationEngineTest {

    private ReconciliationEngine engine;
    private ReconciliationContext context;

    @Before
    public void setup() {
        engine = new ReconciliationEngine();
        context = new ReconciliationContext();
    }

    @Test
    public void testMatchedTransaction() {
        TransactionRecordEntity record = buildRecord("REF1", BigDecimal.TEN, LocalDate.now());

        ReconciliationResultEntity result =
                engine.reconcile(record, context);

        assertEquals("MATCHED", result.getResultCode());
    }

    @Test
    public void testDuplicateTransaction() {
        TransactionRecordEntity record1 = buildRecord("REF1", BigDecimal.TEN, LocalDate.now());
        TransactionRecordEntity record2 = buildRecord("REF1", BigDecimal.TEN, LocalDate.now());

        engine.reconcile(record1, context);
        ReconciliationResultEntity result =
                engine.reconcile(record2, context);

        assertEquals("DUPLICATE", result.getResultCode());
    }

    @Test
    public void testInvalidBusinessDate() {
        TransactionRecordEntity record =
                buildRecord("REF2", BigDecimal.TEN, LocalDate.now().plusDays(1));

        ReconciliationResultEntity result =
                engine.reconcile(record, context);

        assertEquals("INVALID_DATE", result.getResultCode());
    }

    private TransactionRecordEntity buildRecord(
            String ref, BigDecimal amount, LocalDate date) {

        TransactionRecordEntity record = new TransactionRecordEntity();
        record.setReferenceId(ref);
        record.setAmount(amount);
        record.setBusinessDate(date);
        return record;
    }
}
