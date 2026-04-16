# Skill: bootstrap-ui-framework

## Type
styling

## Purpose

Provide a consistent Bootstrap 5 UI approach for Spring applications using either server-rendered HTML or React frontend rendering.

---

## When to Use

Use when product or feature specs require Bootstrap-based UI or responsive component conventions.

## Conflicts

- tailwindcss

---

## Rules

- Use Bootstrap 5 components and utilities as the primary styling system.
- Keep Bootstrap as the presentation layer; do not let it redefine the app's rendering architecture.
- Prefer Bootstrap classes (`container`, `row`, `col-*`, `card`, `btn`, `form-control`, `table`) over ad-hoc custom CSS.
- Keep custom CSS minimal and focused on app polish, not replacing Bootstrap primitives.
- Bootstrap CSS/JS must be served from local project assets (for example `src/main/resources/static/vendor/bootstrap/...`), not CDN links.
- Fonts/icons used by UI must be stored and served from local static assets unless spec explicitly approves external hosting.
- See `.ai/guides/local-ui-assets.md` for concrete local installation and asset wiring steps.

---

## Layout and Responsiveness

- Use `container`/`container-fluid` with `row` + `col-*` for page layout.
- Wrap wide tables in `table-responsive` (or breakpoint variants like `table-responsive-md`).
- Use responsive display utilities for low-priority table columns (for example: `d-none d-md-table-cell`).
- Prefer `navbar-expand-*` with a collapse/toggler for mobile navigation.

---

## Thymeleaf / HTMX Usage

- Keep page composition server-rendered; Bootstrap remains presentation-only.
- Use stable IDs for HTMX targets so Bootstrap layout wrappers survive partial updates.
- When a wrapper is swapped with `innerHTML`, include any required Bootstrap chrome in the returned fragment.

---

## React Usage

- Use Bootstrap CSS classes freely in React components for layout and styling.
- Prefer React-controlled state for interactive UI (`modal`, `dropdown`, `collapse`, `tab`, `offcanvas`) instead of imperative DOM toggling.
- Use Bootstrap JavaScript plugins in React only when their behavior is genuinely needed and not simpler to express with React state/components.
- If a Bootstrap JavaScript plugin is used in React, integrate it through `ref` + effect lifecycle with cleanup; do not let plugin state fight React render state.
- Avoid direct DOM mutation patterns that bypass React's declarative model except as a controlled integration boundary.
- Import only the Bootstrap JavaScript modules actually needed to reduce bundle size.

---

## Forms and Validation

- Use Bootstrap form primitives (`form-label`, `form-control`, `form-select`, `input-group`).
- Pair server validation messages with Bootstrap feedback classes (`invalid-feedback`, `is-invalid`).
- Keep labels explicitly associated with controls (`for` + matching `id`).

---

## Theme and Accessibility

- Prefer Bootstrap 5.3 color modes using `data-bs-theme` on the root element when dark mode is required.
- In React, prefer driving theme mode from app state while applying `data-bs-theme` at the root or scoped wrapper level.
- Keep semantic HTML (`<main>`, table header `scope="col"`, meaningful button/link text).
- Ensure navbar toggles and interactive controls include required ARIA attributes.
- Do not assume Bootstrap visual behavior alone satisfies focus management or keyboard interaction requirements in React-controlled components.

---

## HTMX Compatibility Notes

- HTMX swaps should target wrappers that preserve Bootstrap structure (cards, table containers, form sections).
- When a wrapper is swapped with `innerHTML`, include any required Bootstrap chrome in the returned fragment.

---

## Defaults

- Bootstrap version family: 5.x
- Visual baseline: component-first, utility-assisted
- CSS strategy: Bootstrap first, minimal custom CSS
- Asset delivery: local static files (no CDN by default)
- React strategy: CSS/classes first, React state for behavior, Bootstrap JS only when justified

---

## Anti-Patterns

- Do NOT mix Bootstrap and Tailwind in the same project.
- Do NOT rebuild Bootstrap components with large custom CSS blocks.
- Do NOT use Bootstrap JavaScript widgets when plain HTMX + server rendering is sufficient.
- Do NOT use Bootstrap JavaScript plugins in React as the default solution for stateful UI behavior.
- Do NOT manipulate DOM nodes directly in React when the behavior should be driven by component state.
- Do NOT load Bootstrap, fonts, or icon packs from CDN URLs in templates/layouts by default.
