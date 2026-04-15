# Skill: bootstrap-ui-framework

## Type
styling

## Purpose

Provide a consistent Bootstrap 5 UI approach for server-rendered Spring MVC + Thymeleaf applications.

---

## When to Use

Use when product or feature specs require Bootstrap-based UI or responsive component conventions.

---

## Requirements

- thymeleaf-templates
- spring-mvc-thymeleaf-ui-components

## Conflicts

- tailwindcss

---

## Rules

- Use Bootstrap 5 components and utilities as the primary styling system.
- Keep page composition server-rendered (Thymeleaf); Bootstrap is presentation-only.
- Prefer Bootstrap classes (`container`, `row`, `col-*`, `card`, `btn`, `form-control`, `table`) over ad-hoc custom CSS.
- Keep custom CSS minimal and focused on app polish, not replacing Bootstrap primitives.
- Use stable IDs for HTMX targets so Bootstrap layout wrappers survive partial updates.

---

## Layout and Responsiveness

- Use `container`/`container-fluid` with `row` + `col-*` for page layout.
- Wrap wide tables in `table-responsive` (or breakpoint variants like `table-responsive-md`).
- Use responsive display utilities for low-priority table columns (for example: `d-none d-md-table-cell`).
- Prefer `navbar-expand-*` with a collapse/toggler for mobile navigation.

---

## Forms and Validation

- Use Bootstrap form primitives (`form-label`, `form-control`, `form-select`, `input-group`).
- Pair server validation messages with Bootstrap feedback classes (`invalid-feedback`, `is-invalid`).
- Keep labels explicitly associated with controls (`for` + matching `id`).

---

## Theme and Accessibility

- Prefer Bootstrap 5.3 color modes using `data-bs-theme` on the root element when dark mode is required.
- Keep semantic HTML (`<main>`, table header `scope="col"`, meaningful button/link text).
- Ensure navbar toggles and interactive controls include required ARIA attributes.

---

## HTMX Compatibility Notes

- HTMX swaps should target wrappers that preserve Bootstrap structure (cards, table containers, form sections).
- When a wrapper is swapped with `innerHTML`, include any required Bootstrap chrome in the returned fragment.

---

## Defaults

- Bootstrap version family: 5.x
- Visual baseline: component-first, utility-assisted
- CSS strategy: Bootstrap first, minimal custom CSS

---

## Anti-Patterns

- Do NOT mix Bootstrap and Tailwind in the same project.
- Do NOT rebuild Bootstrap components with large custom CSS blocks.
- Do NOT use Bootstrap JavaScript widgets when plain HTMX + server rendering is sufficient.
