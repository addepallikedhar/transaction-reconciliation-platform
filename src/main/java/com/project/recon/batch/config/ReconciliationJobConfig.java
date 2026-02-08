package com.project.recon.batch.config;

import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;

@Configuration
@RequiredArgsConstructor
public class ReconciliationJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final TransactionRecordReader reader;
    private final TransactionReconciliationProcessor processor;
    private final ReconciliationResultWriter writer;

    @Bean
    public Job reconciliationJob() {
        return jobBuilderFactory.get("reconciliationJob")
                .start(reconciliationStep())
                .build();
    }

    @Bean
    public Step reconciliationStep() {
        return stepBuilderFactory.get("reconciliationStep")
                .<TransactionRecordEntity, ReconciliationResultEntity>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
