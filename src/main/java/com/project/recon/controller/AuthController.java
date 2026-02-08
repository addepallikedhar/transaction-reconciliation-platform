package com.project.recon.controller;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    // demo users (replace with DB later if needed)
    private final Map<String, AppUser> users = Map.of(
            "admin", new AppUser("admin", new BCryptPasswordEncoder().encode("admin123"), Role.ROLE_ADMIN),
            "viewer", new AppUser("viewer", new BCryptPasswordEncoder().encode("viewer123"), Role.ROLE_VIEWER)
    );

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,String> req) {
        AppUser user = users.get(req.get("username"));
        if (user == null || !encoder.matches(req.get("password"), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtUtil.generate(user.getUsername(), user.getRole().name());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
