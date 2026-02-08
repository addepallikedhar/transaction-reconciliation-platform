package com.project.recon.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class FileHashUtil {

    private FileHashUtil() {}

    public static String sha256(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(content.getBytes(StandardCharsets.UTF_8));

            return hashToHex(hash);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to calculate hash", e);
        }
    }

    private static String hashToHex(byte[] hash) {
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}
