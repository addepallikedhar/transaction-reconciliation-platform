package com.project.recon.service;

import com.project.recon.entity.TransactionFileEntity;
import com.project.recon.exception.DuplicateFileException;
import com.project.recon.repository.TransactionFileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileIngestionServiceTest {

    @Mock
    private TransactionFileRepository fileRepo;

    @Mock
    private AuditService auditService;

    @InjectMocks
    private FileIngestionService service;

    @Test(expected = DuplicateFileException.class)
    public void duplicateFileRejected() {

        when(fileRepo.findByFileHash(any()))
                .thenReturn(Optional.ofNullable(
                        new TransactionFileEntity()
                ));

        service.ingest("file.csv", List.of("a,b"));
    }
}
