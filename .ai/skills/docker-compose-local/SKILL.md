# Skill: docker-compose-local

## Type
infrastructure

## Requirements

- postgresql-config

---

## Purpose

Provide a reproducible local runtime for application plus database using Docker Compose.

---

## When to Use

Use when local development or QA needs parity with containerized application/database topology.

---

## Rules

- Keep Compose configuration in version-controlled files (for example `compose.yaml`).
- Define explicit service names for app and database.
- Add database healthcheck and use `depends_on` with healthy condition for app startup ordering.
- Use named volumes for PostgreSQL persistence in local environments.
- Pass runtime configuration through environment variables, not hardcoded credentials.

---

## Defaults

- Local Compose topology includes at least application + PostgreSQL services.
- Database service uses a named volume for persistent data.
- Compose profiles can be used for optional tools/services.
- Startup and shutdown commands are documented in project README/docs.

---

## Validation Checklist

Before finishing:

- `docker compose up` starts app and DB successfully.
- App connects to DB only after DB healthcheck passes.
- Data persists across container restart via named volume.
- Environment variable contract is documented.

---

## Anti-Patterns

- Do NOT commit production secrets in Compose files.
- Do NOT depend on host-specific paths without clear rationale.
- Do NOT omit healthchecks in multi-service startup dependencies.
- Do NOT use Compose as the only deployment strategy for production without policy review.
