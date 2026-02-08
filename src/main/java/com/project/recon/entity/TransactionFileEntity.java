package com.project.recon.entity;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_file")
@Data
public class TransactionFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileHash;
    private String status;

    private LocalDateTime uploadedAt;
}
