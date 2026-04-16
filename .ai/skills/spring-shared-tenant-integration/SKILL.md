# Skill: spring-shared-tenant-integration

## Type
integration

## Selection Mode
secondary

## Requirements

- shared-db-multitenancy
- spring-jpa-flyway-postgres-integration

---

## Purpose

Define application-wide shared-database multitenancy behavior integrated with JPA, migrations, and PostgreSQL runtime.

---

## Integration Rules

- Tenant context resolution must be established before repository access.
- Entity design and migrations must preserve tenant isolation constraints.
- Services must enforce tenant-aware authorization and mutation boundaries.
- Test strategy must include cross-tenant isolation checks for read and write paths.

---

## Defaults

- Shared database with tenant ID isolation is the selected tenancy model.
- Tenant-scoped uniqueness and indexes are part of schema design defaults.
- Feature specs must include tenant-isolation acceptance criteria.

---

## Anti-Patterns

- Do NOT bypass tenant context for convenience in repository/service code.
- Do NOT add cross-tenant admin capabilities without explicit policy and access control rules.
- Do NOT ship tenant-facing features without isolation tests.
