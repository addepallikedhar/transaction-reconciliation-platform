package com.project.recon.entity;

@Entity
@Table(name = "transaction_record")
@Data
public class TransactionRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referenceId;
    private BigDecimal amount;
    private LocalDate businessDate;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private TransactionFileEntity file;
}

