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

    private final JobLauncher jobLauncher;
    private final Job reconciliationJob;

    public void reconcileFile(TransactionFileEntity file) {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("fileId", file.getId())
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(reconciliationJob, params);

        } catch (Exception e) {
            throw new IllegalStateException("Batch job failed", e);
        }
    }
}


