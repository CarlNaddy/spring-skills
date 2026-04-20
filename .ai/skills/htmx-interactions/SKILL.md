# Skill: htmx-interactions

## Type
interactivity

## Purpose

Enable dynamic UI updates using HTMX without a full SPA framework.

---

## When to Use

Use when adding interactivity to server-rendered applications.

---

## Rules

- Use hx-get, hx-post for interactions
- Always define hx-target
- Use hx-swap to control DOM updates
- Build HTMX URLs with Thymeleaf attributes (for example `th:attr="hx-post=@{/users}"`) so routes stay context-relative
- In controllers, branch full-page vs fragment behavior using `HX-Request` header when endpoint behavior differs
- For HTMX-triggered redirects/navigation, use `HX-Redirect` or `HX-Location` on non-3xx responses (htmx headers are not processed on 3xx)

---

## Patterns

- Replace content dynamically
- Load partials from server
- Submit forms via HTMX
- Keep baseline links/forms functional without HTMX (progressive enhancement)
- For multi-region updates (table row append + form reset), prefer `hx-swap="none"` + out-of-band swaps from server fragments
- For HTMX validation errors, set `HX-Retarget` and `HX-Reswap` headers to place error fragments in the intended container

---

## Example

```html
<button 
  hx-get="/users/list" 
  hx-target="#user-list" 
  hx-swap="innerHTML">
  Load Users
</button>
```

---

## Form Example

```html
<form hx-post="/users" hx-target="#user-list">
  <input name="name" />
  <button type="submit">Save</button>
</form>
```

---

## Server Expectation

* Server returns HTML (NOT JSON)
* Prefer fragments for partial updates
* Fragment payload must be structurally valid for target context (especially table updates via OOB `<template><tbody ...>`)

---

## Defaults

* Use `innerHTML` swap for list updates
* Use `outerHTML` for full replacement

---

## Anti-Patterns

* Do NOT use JSON APIs for HTMX
* Do NOT mix with React/Vue
* Do NOT manipulate DOM manually with JS
