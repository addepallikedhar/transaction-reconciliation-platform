package com.project.recon.service;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class ReconciliationServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private ReconciliationService service;

    @Test
    public void testReconcile() {
        TransactionDTO dto = new TransactionDTO("TXN1", 100.0);
        service.reconcile(Collections.singletonList(dto));
        verify(repository, times(1)).save(any());
    }
}
