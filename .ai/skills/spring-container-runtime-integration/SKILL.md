# Skill: spring-container-runtime-integration

## Type
integration

## Requirements

- dockerfile-spring-boot
- docker-vps-deployment

---

## Purpose

Define container build and runtime expectations for deploying Spring applications on Docker-based environments.

---

## Integration Rules

- Deployment artifacts are immutable container images built in CI/release flow.
- Runtime configuration is fully externalized by environment variables or host secret tooling.
- Health endpoints are part of readiness and release verification.
- Compose/deployment configuration separates local and production concerns.

---

## Defaults

- Use Docker Compose for host-level deployment orchestration on VPS.
- Use restart policies and healthchecks on app and dependent services.
- Keep deployment runbooks versioned with the application.

---

## Anti-Patterns

- Do NOT build production images directly on target hosts for normal releases.
- Do NOT deploy with development-only flags/profiles.
- Do NOT leave rollback and incident actions undocumented.
