# Project Specification

---

# 1. Overview

Build a server-rendered web application with authentication and user management.

The application must follow a clean, maintainable architecture using server-side rendering and progressive enhancement.

---

# 2. Stack

## Backend

* Framework: Spring Boot
* Architecture: Spring MVC (server-side rendering)

## Templates

* Engine: Thymeleaf

## Interactivity

* Library: HTMX

## Styling

* TailwindCSS

---

# 3. Features

## Authentication

* Session-based authentication
* Login and logout
* Protected routes

## User Management

* Create user
* List users
* Basic form handling

---

# 4. Architecture

## Rendering Strategy

* Use server-side rendering (SSR)
* HTML is the primary response format
* HTMX is used to enhance interactivity

---

## Communication

* Browser communicates directly with server
* Server returns HTML (NOT JSON)
* HTMX updates parts of the DOM via fragments

---

## UI Composition

* Use reusable UI components
* Use Thymeleaf fragments
* Avoid duplication in templates

---

## Backend Structure

* Controller → handles HTTP requests
* Service → business logic
* Repository → data access

---

# 5. Constraints

## Forbidden

* Do NOT use REST APIs for UI communication
* Do NOT return JSON for frontend rendering
* Do NOT use React, Vue, or other SPA frameworks
* Do NOT mix SSR and SPA approaches

---

## Required

* Use Thymeleaf templates for all UI rendering
* Use HTMX for dynamic interactions
* Use session-based authentication
* Use reusable UI components

---

# 6. Defaults

* Base URL structure:

  * `/login`
  * `/logout`
  * `/users`
* Templates located in:

  * `src/main/resources/templates`
* Use fragment-based updates for HTMX

---

# 7. Naming Conventions

## Templates

* `index.html` → full page
* `list.html` → fragment list
* `form.html` → form UI

---

## Controllers

* Use feature-based naming:

  * `UserController`
  * `AuthController`

---

# 8. Expected Behavior

The system must:

* Render full pages via Thymeleaf
* Update UI dynamically using HTMX
* Use fragments for partial updates
* Maintain consistent UI through reusable components
* Handle authentication via session

---

# 9. Skill Hints (Guidance)

The following types of skills are expected to be used:

* spring-mvc
* thymeleaf-templates
* htmx-interactions
* tailwindcss
* spring-thymeleaf-htmx-integration
* spring-mvc-auth-session
* spring-mvc-thymeleaf-ui-components

---

# 10. Success Criteria

The implementation is successful if:

* No REST APIs are used for UI
* No JSON is returned for rendering
* HTMX is used correctly for dynamic updates
* Templates are structured and reusable
* Authentication works via session
* No conflicting technologies are introduced

---

# 11. Notes

* Prefer simplicity over complexity
* Follow all skill rules strictly
* Do not introduce unnecessary abstractions
