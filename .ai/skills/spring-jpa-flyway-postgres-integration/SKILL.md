# Skill: spring-jpa-flyway-postgres-integration

## Type
integration

## Requirements

- spring-data-jpa
- flyway-migrations
- postgresql-config

---

## Purpose

Define the persistence architecture for Spring applications using JPA + Flyway with PostgreSQL as production baseline.

---

## Architecture Rules

- Keep database schema lifecycle controlled by Flyway migrations.
- Use JPA repositories for aggregate access and service-layer transactions for write workflows.
- Separate profile-specific datasource behavior (local/test/prod) without changing domain contracts.
- Keep persistence changes coupled to migration artifacts in the same feature lifecycle.

---

## Integration Rules

- Any entity schema change must include Flyway migration changes.
- Production-like profiles run with PostgreSQL settings and non-auto schema generation.
- Feature acceptance criteria must include both functional behavior and migration validity.

---

## Defaults

- PostgreSQL is production/staging target.
- Flyway validates schema history during startup/CI.
- H2 is optional for local/test only and must not redefine production contracts.

---

## Anti-Patterns

- Do NOT let JPA auto-DDL drive production schema evolution.
- Do NOT ship schema changes without migration files.
- Do NOT mix inconsistent datasource behaviors across profiles without explicit policy.
