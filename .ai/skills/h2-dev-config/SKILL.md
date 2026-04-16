# Skill: h2-dev-config

## Type
infrastructure

## Requirements

- spring-data-jpa

---

## Purpose

Provide fast local development and test persistence using H2 in dedicated non-production profiles.

---

## When to Use

Use when developers need quick startup and isolated local/test database behavior.

---

## Rules

- Scope H2 settings to explicit local/test profiles only.
- Keep production profile on PostgreSQL; never use H2 as production default.
- Maintain profile parity for schema expectations using Flyway migrations.
- Keep H2-specific behavior from leaking into production SQL assumptions.

---

## Defaults

- H2 is enabled for `local` and/or `test` profiles.
- Production-like profile remains PostgreSQL-first.
- Tests can use H2 for speed when feature behavior does not require PostgreSQL-specific semantics.

---

## Validation Checklist

Before finishing:

- Profile files clearly separate H2 and PostgreSQL configuration.
- Application can run with both H2 local profile and PostgreSQL profile.
- Schema changes still flow through Flyway migrations.
- No production deployment path references H2 datasource URLs.

---

## Anti-Patterns

- Do NOT set H2 as the default datasource for production builds.
- Do NOT write migrations that only work in H2 syntax unless guarded by strategy.
- Do NOT bypass profile isolation by mixing local and prod datasource keys in one profile.
