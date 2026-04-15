# Product requirements (evolving)

## Overview

Build and evolve a server-rendered web application with authentication and user management.

The application follows a clean, maintainable architecture using server-side rendering and progressive enhancement.

---

## Selected stack

- Stack ID: `spring-thymeleaf-htmx`
- Integration skill path: `.ai/skills/spring-thymeleaf-htmx-integration/SKILL.md`
- Required stack skills:
  - `spring-mvc`
  - `thymeleaf-templates`
  - `htmx-interactions`
  - `spring-boot-devtools`
- Baseline app skills (in use for current product):
  - `bootstrap-ui-framework`
  - `spring-mvc-auth-session`
  - `spring-mvc-thymeleaf-ui-components`
- Selection rationale: Matches current SSR Thymeleaf + HTMX app architecture, avoids SPA migration, and aligns with project-wide Bootstrap + local-assets policy.
- Selected by / date: developer / 2026-04-14

### Runtime and framework versions

- Java: `17`
- Spring Boot parent: `3.4.4` (generate code compatible with Spring Boot `3.4.x`)
- Spring Framework and Spring Security versions: managed by the Spring Boot BOM (do not hardcode conflicting versions in dependencies)
- Version change policy: do not upgrade Java/Spring Boot major or minor versions unless explicitly requested by the developer
- Compatibility policy: generated code must target the selected stack and the versions above

---

## Product capabilities

### Authentication

- Session-based authentication
- Login and logout
- Protected routes

### User management

- Create user
- List users
- Delete user
- Basic form handling

---

## Architecture

### Rendering strategy

- Use server-side rendering (SSR)
- HTML is the primary response format
- HTMX is used to enhance interactivity

### Communication

- Browser communicates directly with server
- Server returns HTML (NOT JSON)
- HTMX updates parts of the DOM via fragments

### UI composition

- Use reusable UI components
- Use Thymeleaf fragments
- Avoid duplication in templates

### Backend structure

- Controller -> handles HTTP requests
- Service -> business logic
- Repository -> data access

---

## Constraints

### Forbidden

- Do NOT use REST APIs for UI communication
- Do NOT return JSON for frontend rendering
- Do NOT use React, Vue, or other SPA frameworks
- Do NOT mix SSR and SPA approaches

### Required

- Use Thymeleaf templates for all UI rendering
- Use HTMX for dynamic interactions
- Use session-based authentication
- Use reusable UI components
- Use local static assets for UI dependencies (Bootstrap/Tailwind, fonts, icons); avoid CDN runtime dependencies by default

### Java code style (no Lombok baseline)

- Do not add Lombok as a project dependency by default
- Prefer Java `record` for immutable request/response/view DTOs and other web-facing data carriers
- Prefer constructor-based binding/injection over mutable property patterns where practical
- Use explicit classes only when mutability or framework constraints require it (for example, JPA entities)

---

## Defaults

- Base URL structure:
  - `/`
  - `/login`
  - `/logout`
  - `/users`
- Templates location:
  - `src/main/resources/templates`
- Use fragment-based updates for HTMX interactions

---

## Naming conventions

### Templates

- `index.html` -> full page
- `list.html` -> fragment list
- `form.html` -> form UI

### Controllers

- Use feature-based naming:
  - `UserController`
  - `AuthController`

---

## Expected behavior

- Render full pages via Thymeleaf
- Update UI dynamically using HTMX
- Use fragments for partial updates
- Maintain consistent UI through reusable components
- Handle authentication via session

---

## Current priorities

- Priority 1: Migrate UI from Tailwind to Bootstrap with local assets (`specs/features/004-bootstrap-ui-migration/`)
- Priority 2: Keep per-user todo list behavior stable (`specs/features/003-user-todos/`)
- Priority 3: Keep user detail navigation and delete flows stable (`specs/features/002-user-detail-view/`, `specs/features/001-delete-users/`)

## In scope now

- Migrate current SSR templates/fragments from Tailwind to Bootstrap
- Serve Bootstrap/fonts/icons from local static assets (no CDN runtime dependencies)
- Preserve existing login, user list/detail, and todo behavior during migration

## Out of scope now

- New product capabilities beyond UI migration
- SPA/API architecture changes
- Mixed Tailwind + Bootstrap project baseline

---

## Success criteria

- No REST APIs are used for UI
- No JSON is returned for rendering
- HTMX is used correctly for dynamic updates
- Templates are structured and reusable
- Authentication works via session
- No conflicting technologies are introduced

---

## Notes

- Prefer simplicity over complexity.
- Follow all skill rules strictly.
- Do not introduce unnecessary abstractions.
- Link active feature specs under `specs/features/`.
- Active feature: `specs/features/004-bootstrap-ui-migration/spec.md`

