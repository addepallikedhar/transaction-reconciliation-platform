package com.project.recon.security;

public class JwtUtilTest {

    private JwtUtil util;

    @Before
    public void setup() {
        util = new JwtUtil();
        ReflectionTestUtils.setField(util, "secret",
                "replace-with-very-long-secure-secret-key-1234567890");
        ReflectionTestUtils.setField(util, "expirationMs", 60000L);
    }

    @Test
    public void testGenerateAndParse() {
        String token = util.generate("user","ROLE_ADMIN");
        Claims c = util.parse(token);
        assertEquals("user", c.getSubject());
        assertEquals("ROLE_ADMIN", c.get("role"));
    }
}
