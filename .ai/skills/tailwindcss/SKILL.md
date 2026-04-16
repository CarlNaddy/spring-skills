# Skill: tailwindcss

## Type
styling

## Conflicts

- bootstrap-ui-framework

## When to Use

Use for utility-first CSS styling in server-rendered or React-based applications.

---

## Rules

- Use utility classes as the default styling mechanism.
- Keep utility usage readable and intentional; do not pile on conflicting classes without reason.
- Avoid custom CSS unless utilities cannot express the requirement cleanly.
- Prefer composition through reusable UI components for repeated patterns.

---

## Shared Usage

- Use spacing, layout, typography, color, and responsive utilities directly in markup/components.
- Use state and responsive variants intentionally (`hover:`, `focus:`, `md:`, `dark:`).
- Keep dark mode strategy explicit and consistent across the project.

---

## Thymeleaf / SSR Usage

- Use Tailwind classes directly in server-rendered templates and fragments.
- Keep repeated UI structures in reusable Thymeleaf fragments/components when class sets repeat.

---

## React Usage

- Use Tailwind utilities through `className` in React components.
- Extract repeated UI patterns into reusable React components instead of duplicating long utility strings.
- Keep conditional class logic simple and readable; prefer clear branching over tangled string concatenation.
- Represent UI variants (`size`, `tone`, `state`) through props and predictable class composition.
- Keep presentational components pure and let React state drive visual states.

---

## Defaults

- Tailwind is the primary styling system for the project.
- Component extraction is preferred when the same utility combination appears repeatedly.
- Dark mode uses Tailwind's `dark:` variant with one project-wide strategy.

---

## Anti-Patterns

- Do NOT mix with Bootstrap in the same project.
- Do NOT create unreadable, excessively long inline class strings without extracting a reusable abstraction.
- Do NOT hide important visual logic in scattered conditional class concatenation.
- Do NOT reach for custom CSS first when utilities and component composition are sufficient.

---

## Anti-Patterns

- Do NOT mix with Bootstrap in the same project