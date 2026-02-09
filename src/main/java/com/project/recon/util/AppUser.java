package com.project.recon.util;

import com.project.recon.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUser {
    private String username;
    private String password; // encoded
    private Role role;
}
