package com.project.recon.util;

import com.project.recon.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @Before
    public void setup() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret",
                "very-long-test-secret-key-12345678901234567890");
        ReflectionTestUtils.setField(jwtUtil, "expirationMs", 60000L);
    }

    @Test
    public void generateAndParseToken() {
        String token =
                jwtUtil.generateToken("admin", "ROLE_ADMIN");

        Claims claims = jwtUtil.parseToken(token);

        assertEquals("admin", claims.getSubject());
        assertEquals("ROLE_ADMIN", claims.get("role"));
    }
}
