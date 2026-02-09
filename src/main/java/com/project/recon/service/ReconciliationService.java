package com.project.recon.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
public class ReconciliationService {

    private final JobLauncher jobLauncher;
    private final Job reconciliationJob;

    public ReconciliationService(JobLauncher jobLauncher,
                                 Job reconciliationJob) {
        this.jobLauncher = jobLauncher;
        this.reconciliationJob = reconciliationJob;
    }

    public void runBatch(Long fileId) {
        try {
            jobLauncher.run(
                    reconciliationJob,
                    new JobParametersBuilder()
                            .addLong("fileId", fileId)
                            .addLong("time", System.currentTimeMillis())
                            .toJobParameters()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Batch failed", e);
        }
    }
}
