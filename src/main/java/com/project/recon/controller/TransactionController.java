package com.project.recon.controller;

import com.project.recon.service.ReconciliationService;
import com.project.recon.util.CsvParserUtil;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ReconciliationService service;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestBody List<String> csvLines) {
        List<TransactionDTO> dtos = CsvParserUtil.parse(csvLines);
        service.reconcile(dtos);
        return ResponseEntity.ok("Processed Successfully");
    }
}
