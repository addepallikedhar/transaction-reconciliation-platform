# 📘 Transaction Reconciliation Platform

A production-style **backend reconciliation system** built using **Spring Boot 3**, designed to ingest financial transaction files, reconcile them using configurable business rules, and generate auditable reconciliation results.

This project demonstrates **real-world enterprise backend design**, combining REST APIs, batch processing, security, idempotency, and clean architecture.

---

## 🧠 What Problem Does This Project Solve?

In financial systems (banking, payments, trading), transactions often arrive from **multiple sources** (internal systems, external partners, vendors).  
These records must be **reconciled** to ensure:

- No missing transactions
- No duplicates
- No mismatches in amount or reference
- Full auditability for compliance

This platform simulates that **end-to-end reconciliation workflow**.

---

## 🏗️ High-Level Architecture

```
Client / API Consumer
        ↓
REST Controllers (Security Protected)
        ↓
Service Layer (Business Logic)
        ↓
Rule Engine (Reconciliation Logic)
        ↓
Persistence Layer (JPA / Database)
        ↓
Spring Batch Job (Large Volume Processing)
```

### Architectural Principles
- Clear separation of concerns
- Business logic independent of frameworks
- Stateless APIs
- Batch processing isolated via profiles
- Enterprise-friendly design choices

---

## 🔐 Security Design

- Spring Security–based authentication
- JWT-based stateless authorization
- Role-based access to protected endpoints
- Security configuration isolated from business logic

---

## 📦 Core Features

### 1️⃣ File Ingestion
- Upload transaction files through REST API
- Each file generates a unique `fileId`
- Duplicate uploads prevented using hashing (idempotency)
- File metadata and transactions persisted

---

### 2️⃣ Reconciliation Rule Engine
- Applies configurable business rules
- Matches internal vs external transactions
- Categorizes records into:
  - MATCHED
  - UNMATCHED
  - MISMATCHED
- Designed to easily add new rules

---

### 3️⃣ Batch Processing (Spring Batch)
- Batch job processes large transaction volumes
- Reads ingested transactions
- Applies reconciliation logic
- Writes reconciliation results
- Optimized for scalability and fault tolerance

> In real enterprise systems, batch jobs are validated through controlled runs rather than CI pipelines.  
> This project follows the same approach.

---

### 4️⃣ Audit & Idempotency
- Tracks file processing lifecycle
- Maintains reconciliation history
- Safe re-runs without corrupting data
- Supports compliance and traceability use cases

---

## ⚙️ Application Modes (Important)

The application runs in **two modes**, controlled using Spring profiles.

---

### 🟢 API Mode
Used for:
- File ingestion
- Status queries
- Result retrieval

Activate:
```bash
SPRING_PROFILES_ACTIVE=api
```

---

### 🟣 Batch Mode
Used for:
- Executing reconciliation jobs on ingested data

Activate:
```bash
SPRING_PROFILES_ACTIVE=batch
```

---

## ▶️ How to Run (Quick Start)

### 1️⃣ Build the Application
```bash
mvn clean install
```

---

### 2️⃣ Run in API Mode
```bash
SPRING_PROFILES_ACTIVE=api mvn spring-boot:run
```

Application starts on:
```
http://localhost:8080
```

---

### 3️⃣ Upload a Transaction File
```http
POST /files/upload
Authorization: Bearer <JWT>
```

Example Response:
```json
{
  "fileId": 101,
  "status": "UPLOADED"
}
```

---

### 4️⃣ Run Reconciliation Batch Job
```bash
SPRING_PROFILES_ACTIVE=batch \
mvn spring-boot:run -Dspring-boot.run.arguments=101
```

Where `101` is the `fileId` returned during upload.

---

## 🐳 Running with Docker

### Build Docker Image
```bash
docker build -t transaction-recon-platform .
```

### Run Container
```bash
docker run -p 8080:8080 transaction-recon-platform
```

---

## 🧪 Testing Strategy

### Covered
- Service layer unit tests
- Rule engine tests
- Repository tests
- Controller tests
- Security tests

### Not Included (By Design)
- Full Spring Batch end-to-end job execution tests

**Reason:**  
Batch jobs rely on metadata tables and execution state. In enterprise systems, such jobs are validated via staging environments rather than CI pipelines.

---

## 📌 Technology Stack

- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Batch
- JPA / Hibernate
- H2 (local/testing)
- PostgreSQL (production-ready)
- Docker

---

## 🎯 Summary

This project demonstrates:
- Enterprise backend architecture
- Secure REST APIs
- Batch processing with Spring Batch
- Clean, maintainable design
- Real-world tradeoffs and decisions
