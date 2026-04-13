# Skill: spring-rest-api

## Type
core

## When to Use

Use when building a REST API with Spring Boot.

---

## Rules

- Use @RestController
- Return JSON responses
- Use proper HTTP methods (GET, POST, PUT, DELETE)

---

## Guidelines

- Use DTOs instead of exposing entities
- Follow REST conventions
- Use layered architecture (Controller → Service → Repository)

---

## Anti-Patterns

- Do NOT return HTML
- Do NOT expose database entities directly