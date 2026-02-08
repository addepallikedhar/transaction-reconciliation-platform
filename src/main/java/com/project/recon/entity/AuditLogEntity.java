package com.project.recon.entity;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Data
public class AuditLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityType;
    private Long entityId;
    private String action;
    private String oldValue;
    private String newValue;

    private LocalDateTime createdAt;
}
