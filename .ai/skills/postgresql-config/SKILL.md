# Skill: postgresql-config

## Type
infrastructure

## Requirements

- spring-data-jpa

---

## Purpose

Define production-grade PostgreSQL datasource configuration for Spring Boot applications.

---

## When to Use

Use when PostgreSQL is the primary persistent database for staging/production environments.

---

## Rules

- Configure datasource via externalized environment variables, not hardcoded secrets.
- Keep production-like profile configuration explicit (for example `prod`).
- Set Hibernate schema management to migration-driven mode (`ddl-auto=none` outside tests).
- Keep JDBC URL, credentials, and pool settings configurable per environment.
- Use Actuator health checks to expose database readiness.

---

## Defaults

- PostgreSQL is the default database for production-like profiles.
- Use UTF-8 and UTC-oriented conventions across app and DB settings.
- Keep connection pool sizing explicit and environment-specific.
- Keep SQL initialization and schema lifecycle controlled by Flyway.

---

## Validation Checklist

Before finishing:

- No production credentials are committed to repository files.
- `prod`-profile datasource properties resolve from environment variables.
- Database health endpoint is reachable in runtime checks.
- Application starts with Flyway-managed schema and `ddl-auto=none`.

---

## Anti-Patterns

- Do NOT keep production DB credentials in plain text in committed config files.
- Do NOT use embedded database settings in production profiles.
- Do NOT rely on default pool settings without environment sizing review.
- Do NOT mix schema management strategies (auto-DDL + Flyway) in production-like profiles.
