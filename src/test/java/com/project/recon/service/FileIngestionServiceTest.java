package com.project.recon.service;

@RunWith(MockitoJUnitRunner.class)
public class FileIngestionServiceTest {

    @Mock
    private TransactionFileRepository repository;

    @Mock
    private AuditService auditService;

    @InjectMocks
    private FileIngestionService service;

    @Test
    public void testSuccessfulIngestion() {
        when(repository.findByFileHash(any()))
                .thenReturn(Optional.empty());

        when(repository.save(any()))
                .thenAnswer(inv -> inv.getArgument(0));

        TransactionFileEntity file =
                service.ingestFile("file.csv", List.of("a,b"));

        assertEquals("RECEIVED", file.getStatus());
        verify(auditService).log(any(), any(), eq("CREATED"), any(), eq("RECEIVED"));
    }

    @Test(expected = DuplicateFileException.class)
    public void testDuplicateFile() {
        when(repository.findByFileHash(any()))
                .thenReturn(Optional.of(new TransactionFileEntity()));

        service.ingestFile("file.csv", List.of("a,b"));
    }
}
