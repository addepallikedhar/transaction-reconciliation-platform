package com.project.recon.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
