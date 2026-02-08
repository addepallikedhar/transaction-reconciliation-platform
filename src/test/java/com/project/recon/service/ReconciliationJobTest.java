package com.project.recon.service;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBatchTest
public class ReconciliationJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testJobLaunch() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("fileId", 1L)
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution =
                jobLauncherTestUtils.launchJob(params);

        assertEquals(BatchStatus.COMPLETED, execution.getStatus());
    }
}
