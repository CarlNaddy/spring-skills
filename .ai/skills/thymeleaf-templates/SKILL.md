# Skill: thymeleaf-templates

## Type
templates

## Purpose

Provide structured, reusable, and maintainable HTML templates using Thymeleaf.

---

## When to Use

Use when rendering HTML on the server with Spring MVC.

---

## Core Principles

- HTML-first approach
- Templates must be readable without backend knowledge
- Prefer composition over duplication

---

## Folder Structure

Templates must follow feature-based structure:

```

templates/
layouts/
base.html

fragments/
header.html
footer.html

users/
index.html
list.html
form.html

```

---

## Layout System

Use a base layout and extend it.

### Required Rule (Asset Safety)

- Base layout must own shared `<head>` assets (Tailwind, HTMX, meta, title)
- Shared UI assets (CSS/JS/fonts/icons) must be referenced from local project paths under static resources (no CDN by default)
- Full pages (`index.html`, `login.html`, etc.) must render through the base layout at the root `<html>` level
- Do NOT compose pages by replacing only `<body>`/`<main>` if that bypasses `<head>`

### Base Layout Example

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title th:text="${title}">App</title>
</head>
<body>

  <div th:replace="fragments/header :: header"></div>

  <main th:replace="${content}"></main>

  <div th:replace="fragments/footer :: footer"></div>

</body>
</html>
```

---

## Page Example

```html
<div th:replace="layouts/base :: content(~{::body})">
  <h1>Users</h1>
</div>
```

---

## Fragment Rules

* Use fragments for reusable UI components
* Keep fragments small and focused

### Example

```html
<div th:fragment="userRow(user)">
  <tr>
    <td th:text="${user.name}"></td>
  </tr>
</div>
```

---

## HTMX Compatibility

* Templates must support partial rendering
* Use fragments for HTMX responses
* HTMX fragments must exclude duplicate `<html>/<head>/<body>` wrappers

---

## Expression Rules

* Use `${}` for variables
* Avoid complex logic in templates

---

## Form Handling

* Use `th:action` and `th:object`

### Example

```html
<form th:action="@{/users}" method="post" th:object="${user}">
  <input th:field="*{name}" />
  <button type="submit">Save</button>
</form>
```

---

## Defaults

* Templates directory: `src/main/resources/templates`
* Use semantic HTML
* Keep templates simple

---

## Naming Conventions

- index.html → full page
- list.html → fragment list
- form.html → form UI
- *_row.html → table rows

---

## Anti-Patterns

* Do NOT duplicate HTML structures
* Do NOT embed business logic in templates
* Do NOT mix with SPA frameworks
* Do NOT return full pages for HTMX updates
* Do NOT define Tailwind/HTMX scripts in templates that are not guaranteed to render in final HTML
* Do NOT reference CSS/JS/font/icon CDN URLs in page templates or fragments by default

---

## Validation Checklist

Before finishing:

- Open page source and confirm Tailwind/HTMX scripts are present in `<head>`
- Confirm shared CSS/JS/fonts/icons are loaded from local static paths
- Confirm full-page routes render full document structure
- Confirm HTMX routes return fragment-only HTML

