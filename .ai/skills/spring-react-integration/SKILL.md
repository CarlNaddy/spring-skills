# Skill: spring-react-integration

## Type
integration

## Requirements

- spring-rest-api
- react-frontend
- spring-boot-devtools

## Conflicts

- thymeleaf-templates

## When to Use

Use when combining Spring Boot REST API with React frontend.

---

## Architecture Rules

- Backend and frontend must be separate
- Spring Boot provides JSON APIs only
- React handles UI rendering

---

## Communication

- Use REST API (JSON)
- Base path: /api

---

## Backend Guidelines

- Use @RestController
- Do not return HTML

---

## Frontend Guidelines

- Use fetch or axios for API calls
- Store API base URL in environment variables

---

## Development Setup

- Run frontend and backend separately
- Use proxy for API calls in development

---

## Anti-Patterns

- Do NOT use Thymeleaf
- Do NOT mix SSR and SPA