# Enterprise Transaction Reconciliation System

## Overview
Backend-only Spring Boot application that ingests transaction files, reconciles records, and generates reports.

## Tech Stack
- Java 11
- Spring Boot
- JPA / H2
- JUnit 4, Mockito, PowerMockito

## Features
- CSV file ingestion
- Transaction reconciliation
- REST APIs
- Full unit test coverage
- Static utility mocking

## Run
mvn spring-boot:run

## Test
mvn test

## API
POST /api/transactions/upload
