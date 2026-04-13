# Skill: spring-rest-auth-jwt

## Type
feature

## Requirements

- spring-rest-api

## When to Use

Use when authentication should be stateless.

---

## Rules

- Use JWT tokens for authentication
- Backend must be stateless

---

## Guidelines

- Send token via Authorization header (Bearer)
- Validate token on each request

---

## Anti-Patterns

- Do NOT use session-based authentication