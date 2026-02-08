package com.project.recon.entity;

import java.time.LocalDateTime;

@Entity
@Table(name = "reconciliation_result")
@Data
public class ReconciliationResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resultCode;
    private String remarks;
    private LocalDateTime reconciledAt;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private TransactionRecordEntity transaction;
}
