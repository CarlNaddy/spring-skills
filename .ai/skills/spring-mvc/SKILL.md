# Skill: spring-mvc

## Type
core

## Purpose

Provide server-side rendering using Spring MVC with HTML templates.

---

## When to Use

Use when building applications that render HTML on the server.

---

## Rules

- Use @Controller (NOT @RestController)
- Return template names (not JSON)
- Use Model to pass data to views
- Prefer immutable web model types; use Java `record` for request/response/view DTOs when mutability is not required
- For `@ModelAttribute`, prefer constructor binding for dedicated web-binding objects

---

## Architecture

- Controller → handles HTTP requests
- Service → business logic
- Repository → data access

---

## Routing

- Each route returns a full HTML page OR a fragment
- Use clear URL structure (e.g. /users, /users/{id})

---

## Web Model Design (Java 17)

- Preferred: Java `record` for immutable web-facing objects (request DTOs, response DTOs, view models, form command objects that are constructor-bound)
- Keep web-binding objects separate from domain/persistence objects
- Use mutable classes only when property mutation is required by the use case
- Do NOT use records for JPA entities that require mutable lifecycle behavior

---

## Example

```java
@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }
}
```

---

## Defaults

* Template folder: `src/main/resources/templates`
* Naming: feature-based (users/list.html)

---

## Anti-Patterns

* Do NOT return JSON
* Do NOT use @RestController
* Do NOT mix REST and MVC
