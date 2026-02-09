package com.project.recon.controller;

import com.project.recon.entity.TransactionFileEntity;
import com.project.recon.service.FileIngestionService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
@Profile("api")
public class FileController {

    private final FileIngestionService ingestionService;

    public FileController(FileIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/ingest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> ingest(@RequestBody Map<String, Object> req) {

        String fileName = (String) req.get("fileName");
        List<String> lines = (List<String>) req.get("lines");

        TransactionFileEntity file =
                ingestionService.ingest(fileName, lines);

        return ResponseEntity.ok(
                Map.of(
                        "fileId", file.getId(),
                        "status", file.getStatus()
                )
        );
    }
}
