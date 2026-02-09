package com.project.recon.batch.reader;

import com.project.recon.entity.TransactionRecordEntity;
import com.project.recon.repository.TransactionRecordRepository;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@StepScope
public class TransactionRecordReader
        implements ItemReader<TransactionRecordEntity> {

    private final TransactionRecordRepository repo;

    @Value("#{jobParameters['fileId']}")
    private Long fileId;

    private Iterator<TransactionRecordEntity> iterator;

    public TransactionRecordReader(TransactionRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public TransactionRecordEntity read() {
        if (iterator == null) {
            List<TransactionRecordEntity> records =
                    repo.findByFileId(fileId);
            iterator = records.iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }
}
