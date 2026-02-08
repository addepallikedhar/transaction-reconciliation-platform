package com.project.recon.batch.writer;

import com.project.recon.entity.ReconciliationResultEntity;
import com.project.recon.entity.TransactionRecordEntity;
import com.project.recon.repository.ReconciliationResultRepository;
import com.project.recon.repository.TransactionRecordRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReconciliationResultWriter
        implements ItemWriter<ReconciliationResultEntity> {

    private final ReconciliationResultRepository resultRepository;
    private final TransactionRecordRepository recordRepository;

    @Override
    public void write(List<? extends ReconciliationResultEntity> items) {
        for (ReconciliationResultEntity result : items) {
            TransactionRecordEntity record = result.getTransaction();
            record.setStatus(result.getResultCode());

            resultRepository.save(result);
            recordRepository.save(record);
        }
    }
}
