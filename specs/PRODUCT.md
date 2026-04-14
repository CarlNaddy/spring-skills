# Product requirements (evolving)

## Overview

Build and evolve a server-rendered web application with authentication and user management.

The application follows a clean, maintainable architecture using server-side rendering and progressive enhancement.

---

## Selected stack

- Stack ID: `spring-thymeleaf-htmx`
- Integration skill path: `ai/skills/spring-thymeleaf-htmx-integration/SKILL.md`
- Required stack skills:
  - `spring-mvc`
  - `thymeleaf-templates`
  - `htmx-interactions`
- Baseline app skills (in use for current product):
  - `tailwindcss`
  - `spring-mvc-auth-session`
  - `spring-mvc-thymeleaf-ui-components`
- Selection rationale: Matches current SSR Thymeleaf + HTMX app architecture and avoids SPA migration.
- Selected by / date: developer / 2026-04-14

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

---

## Defaults

- Base URL structure:
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

- Priority 1: Implement delete user flow (`specs/features/001-delete-users/`)
- Priority 2:
- Priority 3:

## In scope now

- Add delete user capability in user management UI and backend flow

## Out of scope now

- Soft delete and audit log
- Bulk delete

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
- Active feature: `specs/features/001-delete-users/spec.md`

