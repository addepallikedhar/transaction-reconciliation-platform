package com.project.recon.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("batch")
@ConditionalOnProperty(
        name = "batch.runner.enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class BatchJobRunner implements CommandLineRunner {


    private final JobLauncher jobLauncher;
    private final Job reconciliationJob;

    public BatchJobRunner(JobLauncher jobLauncher,
                          Job reconciliationJob) {
        this.jobLauncher = jobLauncher;
        this.reconciliationJob = reconciliationJob;
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length == 0) {
            throw new IllegalArgumentException("fileId is required");
        }

        JobParameters params = new JobParametersBuilder()
                .addLong("fileId", Long.parseLong(args[0]))
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(reconciliationJob, params);
        System.exit(0);
    }
}
