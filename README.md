# Transaction Reconciliation Platform (Spring Boot)

## Overview

Transaction Reconciliation Platform is an enterprise-grade, backend-only Spring Boot application designed to ingest, validate, reconcile, and audit financial transaction files.

The system is built to closely resemble real-world backend systems used in banking, fintech, and trading operations, with a strong focus on correctness, idempotency, auditability, and testability.

This project is intentionally built **without UI and without Kafka**, focusing purely on backend engineering concerns.

---

## Key Features

- Idempotent transaction file ingestion using SHA-256 checksum
- Rule-based transaction reconciliation engine
- Chunk-based processing using Spring Batch
- Full audit trail for compliance and traceability
- Clean layered architecture
- Comprehensive unit and batch testing (JUnit 4, Mockito, PowerMockito)

---

## High-Level Architecture

Controller
↓
File Ingestion Service (Idempotency)
↓
Audit Logging
↓
Spring Batch Job
├── ItemReader (DB-based)
├── ItemProcessor (Reconciliation Engine)
└── ItemWriter (Persistence)
↓
Reconciliation Results + Audit Logs

---

## Technology Stack

- Java 11
- Spring Boot 2.7.x
- Spring Data JPA
- Spring Batch
- H2 Database (in-memory)
- Lombok
- Maven
- JUnit 4
- Mockito
- PowerMockito
- Spring Batch Test

---

## Domain Model

- **TransactionFile**
    - Represents an uploaded transaction file
    - Enforces idempotency using file hash
- **TransactionRecord**
    - Individual transaction entries within a file
- **ReconciliationResult**
    - Outcome of reconciliation rules per transaction
- **AuditLog**
    - Tracks entity state changes and actions for compliance

---

## Reconciliation Rules Implemented

| Rule | Result Code |
|----|----|
| Duplicate reference in same file | DUPLICATE |
| Business date in future | INVALID_DATE |
| Amount within tolerance | MATCHED |
| Amount mismatch beyond tolerance | AMOUNT_MISMATCH |

Rules are applied deterministically and are designed to be easily extensible.

---

## Idempotency Strategy

- File content is hashed using **SHA-256**
- Duplicate files are rejected before processing
- Ensures safe retries and prevents double processing

---

## Batch Processing Strategy

- Spring Batch is used for scalable file processing
- Transactions are processed in chunks
- Job is restartable using job parameters
- Business logic is decoupled from batch framework

---

## Testing Strategy

- **Static utilities** tested using PowerMockito
- **Business rules** tested using pure JUnit
- **Service layer** tested with Mockito
- **Batch job wiring** tested using Spring Batch Test
- Negative and edge cases explicitly covered

The project is designed to achieve **high line and branch coverage**, suitable for Sonar-based quality gates.

---

## How to Run the Application

```bash
mvn spring-boot:run
