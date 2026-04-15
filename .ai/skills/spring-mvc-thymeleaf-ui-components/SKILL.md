# Skill: spring-mvc-thymeleaf-ui-components

## Type
ui

## Requirements

- spring-mvc
- thymeleaf-templates

## Conflicts

- react-frontend

---

## Purpose

Provide reusable, consistent UI components for server-rendered applications using Thymeleaf.

---

## When to Use

Use when building UI with:
- Spring MVC
- Thymeleaf templates
- Server-side rendering

---

## Core Principles

- Reusability over duplication
- Consistency across the UI
- Components must be simple and composable
- HTML-first approach

---

## Component Strategy

- Use Thymeleaf fragments for all components
- Each component must have a single responsibility
- Components must accept parameters

---

## Folder Structure

```

templates/
components/
button.html
input.html
card.html
modal.html

````

---

## Component Naming

- button → `button.html`
- input → `input.html`
- user-card → `user-card.html`

---

## Fragment Definition

Each component must define a fragment.

### Example: Button Component

```html
<div th:fragment="button(label, type)">
  <button 
    th:text="${label}"
    th:class="'px-4 py-2 rounded ' + (${type} == 'primary' ? 'bg-blue-500 text-white' : 'bg-gray-200')">
  </button>
</div>
```

---

## Usage Example

```html
<div th:replace="components/button :: button('Save', 'primary')"></div>
```

---

## Composition

Components can be composed of other components.

### Example

```html
<div th:replace="components/card :: card(~{::content})"></div>
```

---

## HTMX Compatibility

* Components must work with HTMX updates
* Avoid relying on global state
* Ensure components render correctly when loaded as fragments

---

## Styling

Use utility-first styling (e.g. TailwindCSS) or consistent CSS classes.

---

## Defaults

* All components live in `templates/components`
* Use fragments exclusively
* Keep components small and focused

---

## Anti-Patterns

* Do NOT duplicate HTML across pages
* Do NOT embed business logic in components
* Do NOT mix with frontend frameworks (React, Vue)
* Do NOT create large monolithic components

