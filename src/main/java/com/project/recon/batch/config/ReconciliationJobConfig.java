package com.project.recon.batch.config;

import com.project.recon.batch.processor.TransactionProcessor;
import com.project.recon.batch.reader.TransactionRecordReader;
import com.project.recon.batch.writer.TransactionWriter;
import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ReconciliationJobConfig {

    @Bean
    public Job reconciliationJob(
            JobRepository jobRepository,
            Step reconciliationStep) {

        return new JobBuilder("reconciliationJob", jobRepository)
                .start(reconciliationStep)
                .build();
    }

    @Bean
    public Step reconciliationStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            TransactionRecordReader reader,
            TransactionProcessor processor,
            TransactionWriter writer) {

        return new StepBuilder("reconciliationStep", jobRepository)
                .<TransactionRecordEntity, ReconciliationResultEntity>chunk(5, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
