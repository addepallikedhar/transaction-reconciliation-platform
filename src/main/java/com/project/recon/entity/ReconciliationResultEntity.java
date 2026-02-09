package com.project.recon.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reconciliation_result")
public class ReconciliationResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result_code", nullable = false)
    private String resultCode;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "reconciled_at", nullable = false)
    private LocalDateTime reconciledAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionRecordEntity transaction;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getResultCode() { return resultCode; }
    public void setResultCode(String resultCode) { this.resultCode = resultCode; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getReconciledAt() { return reconciledAt; }
    public void setReconciledAt(LocalDateTime reconciledAt) { this.reconciledAt = reconciledAt; }

    public TransactionRecordEntity getTransaction() { return transaction; }
    public void setTransaction(TransactionRecordEntity transaction) { this.transaction = transaction; }
}
