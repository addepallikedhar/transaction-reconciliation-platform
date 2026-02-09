package com.project.recon.batch.processor;

import com.project.recon.engine.ReconciliationContext;
import com.project.recon.engine.ReconciliationEngine;
import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TransactionProcessor
        implements ItemProcessor<TransactionRecordEntity, ReconciliationResultEntity> {

    private final ReconciliationEngine engine;
    private final ReconciliationContext context = new ReconciliationContext();

    public TransactionProcessor(ReconciliationEngine engine) {
        this.engine = engine;
    }

    @Override
    public ReconciliationResultEntity process(TransactionRecordEntity record) {
        return engine.reconcile(record, context);
    }
}
