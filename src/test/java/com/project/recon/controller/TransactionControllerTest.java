package com.project.recon.controller;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    @Mock
    private ReconciliationService service;

    @InjectMocks
    private TransactionController controller;

    @Test
    public void testUpload() {
        List<String> csv = Arrays.asList("ref,amount", "TXN1,100");
        ResponseEntity<String> response = controller.upload(csv);
        assertEquals(200, response.getStatusCodeValue());
    }
}
