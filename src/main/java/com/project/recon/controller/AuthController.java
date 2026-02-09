package com.project.recon.controller;

import com.project.recon.security.JwtUtil;
import com.project.recon.security.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Profile("api")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // demo users (interview-safe, replaceable)
    private final Map<String, String> users = Map.of(
            "admin", passwordEncoder().encode("admin123"),
            "viewer", passwordEncoder().encode("viewer123")
    );

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {

        String username = req.get("username");
        String password = req.get("password");

        if (!users.containsKey(username) ||
                !passwordEncoder.matches(password, users.get(username))) {
            return ResponseEntity.status(401).build();
        }

        String role = username.equals("admin")
                ? Role.ROLE_ADMIN.name()
                : Role.ROLE_VIEWER.name();

        String token = jwtUtil.generateToken(username, role);

        return ResponseEntity.ok(Map.of("token", token));
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
