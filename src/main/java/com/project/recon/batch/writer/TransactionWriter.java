package com.project.recon.batch.writer;

import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;
import com.project.recon.repository.ReconciliationResultRepository;
import com.project.recon.repository.TransactionRecordRepository;
import lombok.NonNull;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class TransactionWriter
        implements ItemWriter<ReconciliationResultEntity> {

    private final ReconciliationResultRepository resultRepo;
    private final TransactionRecordRepository recordRepo;

    public TransactionWriter(ReconciliationResultRepository resultRepo,
                             TransactionRecordRepository recordRepo) {
        this.resultRepo = resultRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public void write(@NonNull Chunk<? extends ReconciliationResultEntity> chunk) {
        for (ReconciliationResultEntity result : chunk) {
            TransactionRecordEntity record = result.getTransaction();
            record.setStatus(result.getResultCode());

            recordRepo.save(record);
            resultRepo.save(result);
        }
    }
}
