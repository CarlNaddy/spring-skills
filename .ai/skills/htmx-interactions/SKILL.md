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

---

## Patterns

- Replace content dynamically
- Load partials from server
- Submit forms via HTMX

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

---

## Defaults

* Use `innerHTML` swap for list updates
* Use `outerHTML` for full replacement

---

## Anti-Patterns

* Do NOT use JSON APIs for HTMX
* Do NOT mix with React/Vue
* Do NOT manipulate DOM manually with JS
