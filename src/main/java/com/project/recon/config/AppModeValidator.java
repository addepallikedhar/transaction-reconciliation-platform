package com.project.recon.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppModeValidator {

    @Value("${app.mode}")
    private String mode;

    @PostConstruct
    public void validate() {
        if (!"api".equals(mode) && !"batch".equals(mode)) {
            throw new IllegalStateException(
                    "Invalid app.mode: " + mode + " (expected api or batch)"
            );
        }
        System.out.println("Application running in mode: " + mode);
    }
}
