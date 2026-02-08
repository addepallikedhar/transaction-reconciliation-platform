package com.project.recon.util;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileHashUtil.class)
public class FileHashUtilTest {

    @Test
    public void testSha256GeneratesHash() {
        String hash1 = FileHashUtil.sha256("content");
        String hash2 = FileHashUtil.sha256("content");

        assertNotNull(hash1);
        assertEquals(hash1, hash2);
    }

    @Test(expected = IllegalStateException.class)
    public void testSha256Exception() throws Exception {
        PowerMockito.mockStatic(MessageDigest.class);
        when(MessageDigest.getInstance("SHA-256"))
                .thenThrow(new NoSuchAlgorithmException());

        FileHashUtil.sha256("test");
    }
}
