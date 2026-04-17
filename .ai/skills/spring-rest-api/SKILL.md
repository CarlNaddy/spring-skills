# Skill: spring-rest-api

## Type
core

## Purpose

Provide a production-grade baseline for designing and implementing JSON REST APIs with Spring Boot 3.4.x.

---

## When to Use

Use when:
- Building HTTP APIs for SPA, mobile, or server-to-server clients.
- Endpoints must return JSON payloads.
- API behavior requires explicit contracts, validation, and consistent error handling.

---

## Rules (Mandatory)

- Use `@RestController` for API endpoints.
- Return JSON responses with explicit status codes.
- Use proper HTTP semantics (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`).
- Keep a layered architecture (`Controller -> Service -> Repository`).
- Use DTOs for request/response contracts; do not expose persistence entities directly.

---

## Entry Points

- Configure controller endpoints and request mappings.
- Configure service-layer business logic and transaction boundaries.
- Configure `application.yml` for API/runtime properties.
- Configure exception handling (`@RestControllerAdvice`) for consistent error payloads.

---

## API Contract Baseline

- Prefer plural resource names (`/users`, `/orders/{id}`).
- Use nouns for resources; avoid RPC-style verbs in URLs unless explicitly needed.
- Keep request/response schemas stable and version intentionally when breaking changes are introduced.
- For create operations, return `201 Created` with `Location` when a new resource URI is available.
- For delete operations, return `204 No Content` when no response body is needed.

---

## Validation and Input Safety

- Use Jakarta Bean Validation annotations on DTOs (for example `@NotBlank`, `@Email`, `@Size`).
- Use `@Valid` on request bodies and relevant method parameters.
- Use method validation (`@Validated`) in services when parameter constraints must be enforced beyond controllers.
- Reject invalid input with deterministic, machine-readable responses.

---

## Error Handling (Required)

- Standardize on RFC 9457 Problem Details (`application/problem+json`) for error responses.
- Enable Spring MVC Problem Details support in configuration.
- Centralize exception-to-response mapping in `@RestControllerAdvice`.
- Avoid leaking internal exception details, SQL fragments, or stack traces to clients.

Recommended configuration:

```yml
spring:
  mvc:
    problemdetails:
      enabled: true
```

---

## Security Baseline

- Secure all business endpoints by default; explicitly allow only required public endpoints.
- Keep authentication and authorization policies explicit at endpoint/service boundaries.
- Validate caller identity and permissions before sensitive state changes.
- Never rely on client-provided authorization claims without server-side verification.

---

## Pagination, Sorting, and Filtering

- Use `Pageable` for list endpoints that can grow.
- Set sane defaults and strict upper bounds using `spring.data.web.pageable.*`.
- Validate/filter user-provided sort fields to prevent accidental expensive queries.
- Keep list endpoints deterministic by including stable sort behavior.

---

## Configuration File Policy

- Use `application.yml` as the canonical Spring Boot configuration file.
- Use `application-<profile>.yml` for environment-specific settings.
- Do not add new `application.properties` files.
- Do not mix `.properties` and `.yml` in the same module.

---

## Testing and Verification (Required)

When API behavior changes, add/maintain tests that verify:
- Happy-path success and correct status codes.
- Validation failures with structured error payloads.
- Unauthorized and forbidden access behavior where security applies.
- Not-found and conflict scenarios (`404`, `409`) where applicable.
- Pagination/sorting behavior and bounds.

Use focused slice tests (`@WebMvcTest` + `MockMvc`/`MockMvcTester`) for controller contracts.
Use broader integration tests (`@SpringBootTest`) for end-to-end behavior.

---

## Output (Required)

The implementation MUST include:

- At least one `@RestController` endpoint with request/response DTOs.
- Service-layer class for business logic (not controller-only logic).
- Centralized API exception handling (`@RestControllerAdvice`).
- Validation annotations and failing-validation test coverage.
- API tests for status codes and JSON contracts.
- `application.yml` entries for API-related configuration.

---

## Anti-Patterns

- Do NOT return HTML from REST endpoints.
- Do NOT expose entities directly as API payloads.
- Do NOT implement business logic in controllers.
- Do NOT return ad-hoc, inconsistent error payloads.
- Do NOT skip validation and rely on database constraints alone.
- Do NOT use broad catch-all handlers that hide domain-specific errors.

---

## References (Context7-aligned)

- Spring Boot 3.4 Externalized Configuration: https://docs.spring.io/spring-boot/3.4/reference/features/external-config.html
- Spring Boot 3.4 Validation: https://docs.spring.io/spring-boot/3.4/reference/io/validation.html
- Spring Boot 3.4 MVC Error Handling and Problem Details: https://docs.spring.io/spring-boot/3.4/reference/web/servlet.html
- Spring Boot 3.4 Testing (WebMvcTest, MockMvc, SpringBootTest): https://docs.spring.io/spring-boot/3.4/reference/testing/spring-boot-applications.html