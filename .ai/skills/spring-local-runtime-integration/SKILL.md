# Skill: spring-local-runtime-integration

## Type
integration

## Selection Mode
secondary

## Requirements

- h2-dev-config
- docker-compose-local

---

## Purpose

Define a local runtime strategy that supports fast H2 development and PostgreSQL parity via Docker Compose.

---

## Integration Rules

- Keep local profile defaults simple for developer speed.
- Provide a PostgreSQL Compose path for parity checks before deployment-sensitive changes.
- Keep profile switching explicit and documented.
- Ensure both local runtime modes are compatible with Flyway migrations.

---

## Defaults

- Developers may use H2 for fast edit/run loops.
- Compose-backed PostgreSQL is used for parity verification and integration checks.
- Runtime docs include profile activation and Compose command examples.

---

## Anti-Patterns

- Do NOT assume H2-only behavior will match PostgreSQL in all cases.
- Do NOT leave local runtime mode undocumented.
- Do NOT skip PostgreSQL parity checks for migration-heavy features.
