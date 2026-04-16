# Skill: flyway-migrations

## Type
core

## Requirements

- spring-data-jpa

---

## Purpose

Provide deterministic database schema evolution with Flyway migrations.

---

## When to Use

Use when schema changes must be versioned, reviewable, and reproducible across environments.

---

## Rules

- Store migrations under `src/main/resources/db/migration`.
- Use versioned migration naming `V<version>__<description>.sql`.
- Use repeatable migrations `R__<description>.sql` only for objects intended to be re-applied on checksum change (for example views).
- Use `flyway validate` in CI or pre-release checks.
- Baseline existing databases explicitly before first managed migration.
- Use `repair` only to recover schema history consistency after confirmed operator review.

---

## Defaults

- Version numbers are monotonically increasing.
- Keep each migration focused on one logical schema change.
- Migration files are immutable after release; fix forward with new migrations.
- Production-like environments run with validation enabled.

---

## Validation Checklist

Before finishing:

- New schema changes include migration files.
- Migration naming follows Flyway conventions.
- Local and CI runs pass `validate`.
- Baseline strategy is documented when integrating legacy schemas.

---

## Anti-Patterns

- Do NOT edit previously applied versioned migrations.
- Do NOT depend on runtime auto-DDL instead of migrations in non-test profiles.
- Do NOT run destructive commands like `clean` in shared environments.
- Do NOT hide migration order or ownership in ad-hoc SQL scripts.
