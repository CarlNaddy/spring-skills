# Skill: spring-thymeleaf-htmx-integration

## Type
integration

## Selection Mode
primary

## Requirements

- spring-mvc
- thymeleaf-templates
- htmx-interactions
- spring-boot-devtools

## Conflicts

- react-frontend
- spring-rest-api

---

## Purpose

Define a production-grade, server-driven architecture using Spring MVC, Thymeleaf, and HTMX.

---

## When to Use

Use when:
- Building an SSR web application with Spring MVC.
- Rendering UI with Thymeleaf templates and fragments.
- Using HTMX for partial page updates without moving to SPA architecture.

Do not use when the stack requires JSON-first REST UI communication.

---

## Architecture Rules (Mandatory)

- Use server-side rendering (SSR) as the primary rendering model.
- Treat HTML as the UI source of truth.
- Use HTMX only as progressive enhancement, not as a SPA replacement.
- Return HTML for UI flows; do not return JSON for view updates.
- Keep UI state and authorization logic on the server.

---

## Entry Points

- Configure full-page MVC endpoints for initial page loads.
- Configure fragment endpoints for HTMX partial updates.
- Configure Thymeleaf layout and fragment structure.
- Configure CSRF-safe form and HTMX interactions.
- Configure `application.yml` for Thymeleaf and runtime integration settings.

---

## Rendering Strategy

- Serve full pages for normal browser requests.
- Serve fragment responses for HTMX requests.
- Ensure full-page responses always include layout composition so shared `<head>` assets (CSS/JS/meta) are present.
- Do not return a fragment template as a top-level full page.

---

## Controller Design

- Keep separate handlers or branching logic for full-page and fragment responses.
- Use request context (for example `HX-Request` header) to decide full-page vs fragment rendering when sharing a route.
- Keep business logic in services; controllers orchestrate view model population.

Example:

```java
@GetMapping("/users")
public String usersPage(Model model,
                        @RequestHeader(value = "HX-Request", required = false) String hxRequest) {
    model.addAttribute("users", userService.findAll());
    if ("true".equalsIgnoreCase(hxRequest)) {
        return "users/index :: userList";
    }
    return "users/index";
}
```

---

## Thymeleaf Fragments

```html
<div th:fragment="userList">
  <ul>
    <li th:each="user : ${users}" th:text="${user.name}"></li>
  </ul>
</div>
```

---

## HTMX Interaction Rules

- Target stable container elements and keep swap scope narrow.
- Prefer explicit `hx-target` and `hx-swap` settings for predictable updates.
- Use response headers such as `HX-Redirect`, `HX-Location`, and `HX-Trigger` when server-driven navigation/events are needed.
- Remember htmx response headers are not processed on 3xx responses; return appropriate non-3xx responses when htmx headers are required.
- Keep progressive enhancement: baseline links/forms must work without HTMX.

---

## CSRF Handling

- Keep CSRF protection enabled for SSR flows.
- Include CSRF token in state-changing forms.
- Ensure HTMX state-changing requests include CSRF token as header or parameter.
- Never disable CSRF to "fix" HTMX requests.

---

## Validation and UX Consistency

- Bind forms to DTOs and render validation errors in fragment and full-page modes consistently.
- Ensure validation failures return the correct fragment for HTMX requests and full template for normal requests.
- Keep user-visible feedback deterministic across both request modes.

---

## Configuration File Policy

- Use `application.yml` as the canonical Spring Boot configuration file.
- Use `application-<profile>.yml` for environment-specific settings.
- Do not add new `application.properties` files.
- Do not mix `.properties` and `.yml` in the same module.

---

## Development and Runtime Defaults

- HTML-first architecture with fragment-based updates.
- Minimal custom JavaScript.
- Keep template/static-resource caching optimized for production.
- Use Spring Boot DevTools for development-time cache relaxation; do not carry dev-only cache settings into production by accident.

---

## Testing and Verification (Required)

When integration behavior changes, verify:
- Full-page request returns complete layout with expected shared assets.
- HTMX request returns only intended fragment payload.
- Fragment fallback works when HTMX is unavailable.
- CSRF enforcement holds for state-changing interactions.
- Validation errors render correctly in both full and fragment responses.

---

## Output (Required)

The implementation MUST include:

- At least one full-page endpoint and one HTMX fragment response path.
- Thymeleaf template(s) with reusable fragment(s) (`th:fragment`).
- HTMX-enabled UI action with explicit target/swap behavior.
- CSRF-safe forms and HTMX mutation flow.
- Tests covering full-page and HTMX fragment responses.
- `application.yml` entries for relevant Thymeleaf/runtime settings.

---

## Anti-Patterns

- Do NOT build JSON REST endpoints for SSR view updates.
- Do NOT return JSON where HTML fragments/pages are required.
- Do NOT mix SPA frameworks into this integration path.
- Do NOT duplicate domain logic in frontend scripts.
- Do NOT structure layouts in a way that drops shared global assets from full-page responses.
- Do NOT break non-HTMX baseline behavior.

---

## References (Context7-aligned)

- Thymeleaf docs (layouts/fragments/forms): https://github.com/thymeleaf/thymeleaf-docs
- HTMX docs (attributes and response headers): https://github.com/bigskysoftware/htmx/tree/v2.0.4/www/content
- Spring Boot 3.4 Thymeleaf properties: https://docs.spring.io/spring-boot/3.4/appendix/application-properties/index.html
- Spring Boot DevTools (development cache defaults): https://docs.spring.io/spring-boot/3.4/reference/using/devtools.html
