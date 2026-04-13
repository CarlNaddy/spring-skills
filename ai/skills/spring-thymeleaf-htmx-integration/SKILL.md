# Skill: spring-thymeleaf-htmx-integration

## Type
integration

## Requirements

- spring-mvc
- thymeleaf-templates
- htmx-interactions

## Conflicts

- react-frontend
- spring-rest-api

---

## Purpose

Define a server-driven architecture using Spring MVC, Thymeleaf, and HTMX.

---

## Architecture Rules

- Use server-side rendering (SSR)
- HTML is the source of truth
- HTMX enhances interactivity (no SPA)
- Backend returns HTML, not JSON

---

## Rendering Strategy

- Full page → normal requests
- Partial fragments → HTMX requests

---

## Controller Design

- Separate endpoints for:
  - full pages
  - partial updates

---

## Example

```java
@GetMapping("/users")
public String page(Model model) {
    model.addAttribute("users", userService.findAll());
    return "users/index";
}

@GetMapping("/users/list")
public String fragment(Model model) {
    model.addAttribute("users", userService.findAll());
    return "users/list :: userList";
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

## HTMX Integration

* HTMX calls fragment endpoints
* Fragments update specific DOM elements

---

## CSRF Handling

* Include CSRF token in forms
* Ensure HTMX requests include CSRF headers

---

## Progressive Enhancement

* App must work without HTMX
* HTMX only enhances UX

---

## Defaults

* HTML-first approach
* Fragment-based updates
* Minimal JavaScript

---

## Anti-Patterns

* Do NOT build REST APIs for UI
* Do NOT return JSON
* Do NOT mix SPA frameworks
* Do NOT duplicate logic in frontend

