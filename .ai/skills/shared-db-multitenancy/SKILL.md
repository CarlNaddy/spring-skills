# Skill: shared-db-multitenancy

## Type
feature

## Requirements

- spring-data-jpa
- postgresql-config

---

## Purpose

Provide a shared-database multi-tenant baseline with strict tenant isolation at application and query layers.

---

## When to Use

Use when one application instance serves multiple tenants in a single database with tenant-scoped data access.

---

## Rules

- Resolve tenant context at request boundary and propagate it through service/repository calls.
- Tenant-owned tables must include tenant identifier columns and indexes.
- All tenant-scoped reads/writes must enforce tenant predicates; never rely on client-provided filtering alone.
- Enforce tenant safety in service methods that mutate data.
- Keep tenant bootstrap, default tenant policies, and cross-tenant admin operations explicit in specs.

---

## Defaults

- Tenant context comes from trusted server-side resolution strategy (not arbitrary request body values).
- Repository methods include tenant-aware query paths.
- Unique constraints for tenant-owned resources are scoped by tenant.
- Feature acceptance criteria include tenant isolation tests.

---

## Validation Checklist

Before finishing:

- Tenant context resolution is explicit and test-covered.
- Tenant-scoped repositories and services cannot return cross-tenant data.
- Shared reference data and tenant-owned data boundaries are documented.
- Admin-level cross-tenant operations require explicit privileged paths.

---

## Anti-Patterns

- Do NOT run tenant-scoped queries without tenant predicates.
- Do NOT trust user-submitted tenant IDs as sole authorization mechanism.
- Do NOT mix tenant and global write paths without explicit policy and tests.
- Do NOT add multitenancy implicitly without stack/policy selection.
