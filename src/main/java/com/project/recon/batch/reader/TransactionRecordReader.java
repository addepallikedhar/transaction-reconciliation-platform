package com.project.recon.batch.reader;

import com.project.recon.entity.TransactionRecordEntity;
import com.project.recon.repository.TransactionRecordRepository;

import java.util.List;

@Component
@StepScope
@RequiredArgsConstructor
public class TransactionRecordReader
        implements ItemReader<TransactionRecordEntity> {

    private final TransactionRecordRepository repository;

    @Value("#{jobParameters['fileId']}")
    private Long fileId;

    private Iterator<TransactionRecordEntity> iterator;

    @Override
    public TransactionRecordEntity read() {
        if (iterator == null) {
            List<TransactionRecordEntity> records =
                    repository.findByFileId(fileId);
            iterator = records.iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }
}
