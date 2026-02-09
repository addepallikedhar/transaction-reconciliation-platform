package com.project.recon.controller;

import com.project.recon.service.ReconciliationService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/batch")
@Profile("api")
public class BatchController {

    private final ReconciliationService service;

    public BatchController(ReconciliationService service) {
        this.service = service;
    }

    @PostMapping("/run")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> run(@RequestBody Map<String, Long> req) {

        Long fileId = req.get("fileId");
        service.runBatch(fileId);

        return ResponseEntity.ok("Batch triggered");
    }
}
