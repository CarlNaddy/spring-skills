# Skill: dockerfile-spring-boot

## Type
infrastructure

## Requirements

- postgresql-config

---

## Purpose

Define a secure and reproducible Docker image strategy for Spring Boot applications.

---

## When to Use

Use when the application must run as a container in local, staging, or production environments.

---

## Rules

- Use multi-stage builds to separate build tooling from runtime image.
- Run container with a non-root user.
- Keep image configuration environment-driven (profiles, datasource, ports).
- Expose health endpoint contract for runtime checks.
- Keep image artifacts deterministic and versioned by CI/release flow.

---

## Defaults

- Runtime image contains only the packaged app and required runtime dependencies.
- Container starts via `java -jar` with configurable JVM options.
- Build context excludes unnecessary files via `.dockerignore`.

---

## Validation Checklist

Before finishing:

- Image builds successfully from clean checkout.
- Container starts and reports healthy status through app health endpoint.
- Container runtime user is non-root.
- Runtime configuration can be overridden via environment variables.

---

## Anti-Patterns

- Do NOT run application containers as root by default.
- Do NOT copy build caches and local secrets into runtime image.
- Do NOT hardcode environment-specific values in Dockerfile.
- Do NOT skip health endpoint exposure when containerization is required.
