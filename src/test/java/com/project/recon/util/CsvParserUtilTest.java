package com.project.recon.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CsvParserUtil.class)
public class CsvParserUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCsv() {
        CsvParserUtil.parse(Collections.emptyList());
    }

    @Test
    public void testValidCsv() {
        List<String> lines = Arrays.asList(
                "ref,amount",
                "TXN1,100.0"
        );
        List<TransactionDTO> result = CsvParserUtil.parse(lines);
        assertEquals(1, result.size());
    }
}
