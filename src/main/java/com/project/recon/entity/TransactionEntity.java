package com.project.recon.entity;

@Entity
@Table(name = "transactions")
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referenceId;
    private Double amount;
    private String status; // MATCHED / UNMATCHED
}
