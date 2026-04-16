# Skill: spring-data-jpa

## Type
core

## Purpose

Provide a consistent persistence layer using Spring Data JPA for Spring Boot applications.

---

## When to Use

Use when application data must be stored in a relational database and accessed through repositories.

---

## Rules

- Keep persistence model separate from web model (DTO/form/view objects).
- Use `@Entity` classes for database mapping; do not use Java `record` for mutable JPA entities.
- Use Spring Data repository interfaces (`JpaRepository`) for aggregate access.
- Keep transactional write orchestration in services (`@Transactional` on service methods), not controllers.
- Avoid exposing entities directly in HTML/API contracts.
- Prefer explicit fetch and query design; avoid accidental N+1 query patterns in list/detail flows.

---

## Defaults

- Package entities and repositories by feature area.
- Persist enums explicitly with stable mapping (`EnumType.STRING` when applicable).
- Use optimistic locking (`@Version`) for entities with concurrent write risk.
- Keep schema lifecycle managed by migrations (Flyway), not ad-hoc runtime schema generation.

---

## Validation Checklist

Before finishing:

- Entity boundaries are separate from form/request objects.
- Repository interfaces are focused on aggregate use cases.
- Write operations execute inside service-layer transactions.
- Read-heavy endpoints are checked for query efficiency.

---

## Anti-Patterns

- Do NOT place persistence logic in controllers/templates.
- Do NOT rely on `spring.jpa.hibernate.ddl-auto=update` in production-like profiles.
- Do NOT use database entities as direct response/view contracts.
- Do NOT leave transaction boundaries implicit for multi-step writes.
