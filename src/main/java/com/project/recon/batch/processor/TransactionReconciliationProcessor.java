package com.project.recon.batch.processor;

import com.project.recon.engine.ReconciliationContext;
import com.project.recon.engine.ReconciliationEngine;
import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;

@Component
@StepScope
@RequiredArgsConstructor
public class TransactionReconciliationProcessor
        implements ItemProcessor<TransactionRecordEntity, ReconciliationResultEntity> {

    private final ReconciliationEngine engine;
    private final ReconciliationContext context = new ReconciliationContext();

    @Override
    public ReconciliationResultEntity process(TransactionRecordEntity record) {
        return engine.reconcile(record, context);
    }
}
