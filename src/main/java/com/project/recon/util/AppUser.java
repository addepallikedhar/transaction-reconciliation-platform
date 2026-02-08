package com.project.recon.util;

@Data
@AllArgsConstructor
public class AppUser {
    private String username;
    private String password; // encoded
    private Role role;
}
