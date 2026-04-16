# Skill: docker-vps-deployment

## Type
infrastructure

## Requirements

- dockerfile-spring-boot
- docker-compose-local

---

## Purpose

Provide a deployment baseline for running containerized Spring applications on generic Docker hosts or VPS environments.

---

## When to Use

Use when deploying outside managed PaaS/Kubernetes, using Docker engine and host-level operations.

---

## Rules

- Keep deployment configuration environment-driven (`.env` or host secret management), never hardcoded.
- Use Compose files (or override files) that separate local and production concerns.
- Define container restart policy and healthcheck behavior explicitly.
- Keep reverse-proxy integration contract explicit (hostnames, forwarded headers, TLS termination boundary).
- Document zero-downtime/rollback procedure at least at operational checklist level.

---

## Defaults

- Production deployment uses prebuilt images, not ad-hoc host compilation.
- Database persistence uses managed volume strategy and backup policy.
- Health endpoint is part of deploy verification.
- Deployment docs include startup, update, rollback, and incident basics.

---

## Validation Checklist

Before finishing:

- Deployment runbook exists for VPS target.
- App container starts from immutable image and passes healthcheck.
- Reverse-proxy and app port contract is documented.
- Rollback path is tested or clearly documented.

---

## Anti-Patterns

- Do NOT deploy with local development Compose configuration unchanged.
- Do NOT keep deployment secrets in committed files.
- Do NOT skip backup/restore considerations for PostgreSQL data volumes.
- Do NOT rely on manual, undocumented host steps for routine releases.
