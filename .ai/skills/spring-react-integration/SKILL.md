# Skill: spring-react-integration

## Type
integration

## Selection Mode
primary

## Requirements

- spring-rest-api
- react-frontend
- spring-boot-devtools

## Conflicts

- thymeleaf-templates

---

## Purpose

Define a production-grade integration between a Spring Boot REST backend and a React SPA frontend.

---

## When to Use

Use when:
- Building a client-rendered SPA with React.
- Backend serves JSON APIs for UI and integrations.
- Frontend and backend can run independently in development and deployment.

Do not use when the selected architecture is server-rendered Thymeleaf + HTMX.

---

## Architecture Rules (Mandatory)

- Keep backend and frontend as separate runtime concerns.
- Spring Boot serves JSON APIs only for UI flows in this integration.
- React handles UI rendering, routing, and client-side interaction state.
- Do not mix SSR Thymeleaf templates into the SPA path.
- Keep domain/business rules on the backend; frontend handles presentation and UX state.

---

## Entry Points

- Configure Spring Boot API endpoints under `/api/**`.
- Configure React API client/base URL handling via environment variables.
- Configure CORS for allowed frontend origins and methods.
- Configure frontend routing fallback strategy in the frontend host layer.
- Configure `application.yml` for backend runtime and API integration settings.

---

## Dependency Cross-Links

Use this integration skill together with its required skills as a single execution graph:

- `spring-rest-api` for API contract quality, validation, and error handling.
- `react-frontend` for client rendering model, state handling, and routing.
- `spring-boot-devtools` for development-time productivity and safe local loops.

Do not pull `thymeleaf-templates` patterns into this integration path.

---

## API Communication Contract

- Use JSON request/response payloads.
- Keep API base path stable (default: `/api`).
- Use explicit DTO contracts and version intentionally when breaking response changes are required.
- Return correct HTTP status codes and structured error payloads.
- Ensure auth/session/token mechanics are explicit and aligned with selected auth skill.

---

## CORS and Security Baseline

- Configure CORS explicitly (origins, methods, headers, credentials policy).
- Default to strict allowlists; avoid wildcard origins in production.
- Scope CORS mappings to API paths (`/api/**`) instead of global permissive rules.
- Do not rely on client-side checks for authorization decisions.
- Keep CSRF/auth configuration aligned with chosen auth model (session or JWT).

Spring MVC global CORS example:

```java
@Configuration(proxyBeanMethods = false)
class CorsConfig {
    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("https://app.example.com")
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                    .allowedHeaders("Authorization", "Content-Type")
                    .allowCredentials(true);
            }
        };
    }
}
```

---

## Frontend Integration Rules

- Use a dedicated API client layer (do not scatter raw fetch logic across unrelated components).
- Keep server-state loading and mutation logic explicit and testable.
- Handle loading, error, and empty states for every remote data view.
- Use environment variables for backend base URL and avoid hardcoded hostnames.
- Preserve progressive delivery boundaries (frontend can be deployed independently of backend releases when contracts are compatible).

---

## Development Setup

- Run frontend and backend separately in development.
- Use a dev proxy or explicit base URL to route `/api` calls.
- Keep local environment variables in local-only files and never commit secrets.
- Keep hot-reload/devtools settings development-only.

---

## Configuration File Policy

- Use `application.yml` as the canonical Spring Boot configuration file.
- Use `application-<profile>.yml` for environment-specific settings.
- Do not add new `application.properties` files.
- Do not mix `.properties` and `.yml` in the same module.

---

## Testing and Verification (Required)

When integration behavior changes, verify:
- API contract behavior with controller/integration tests.
- CORS behavior for expected frontend origins and blocked origins.
- Frontend API workflows for success, error, and timeout/retry paths.
- Routing behavior for deep links and refresh scenarios.
- Authentication/authorization behavior across frontend and backend boundaries.

---

## Execution Checklist (Quick)

- [ ] API routes are under `/api/**` with DTO-based contracts.
- [ ] Frontend API base URL is environment-driven (no hardcoded hosts).
- [ ] CORS allowlist is explicit and scoped to API paths.
- [ ] Core API-driven screens handle loading, error, and empty states.
- [ ] Contract/security tests exist for critical backend endpoints.
- [ ] Frontend tests cover key integration user flows.

---

## Output (Required)

The implementation MUST include:

- Spring Boot REST endpoints under `/api/**` with DTO-based contracts.
- React frontend consuming API through a dedicated client layer.
- Backend CORS configuration scoped to API paths.
- Environment-based API base URL configuration for frontend.
- Integration tests for API contract and security behavior.
- Frontend tests for key API-driven UI states.
- `application.yml` entries for backend integration settings.

---

## Anti-Patterns

- Do NOT return server-rendered HTML for SPA API endpoints.
- Do NOT mix Thymeleaf SSR templates into the React integration flow.
- Do NOT use wildcard CORS origin settings in production.
- Do NOT hardcode API hosts/ports in component code.
- Do NOT duplicate business validation rules only in frontend code.
- Do NOT expose entities directly from API controllers.

---

## References (Context7-aligned)

- Spring Boot 3.4 CORS support (Spring MVC): https://docs.spring.io/spring-boot/3.4/reference/web/servlet.html
- Spring Boot 3.4 static resources and cache handling: https://docs.spring.io/spring-boot/3.4/reference/web/servlet.html
- React documentation (official): https://react.dev
- React docs repository (reference source): https://github.com/reactjs/react.dev
