# Feature spec: Bootstrap UI migration

## Summary

Migrate the current server-rendered UI from Tailwind CSS to Bootstrap 5 with local static assets, while preserving existing SSR + HTMX behavior and user flows.

## Problem

The project currently uses Tailwind-based styling while the framework standards now require one project-wide UI framework and establish a Bootstrap-first path with no CDN runtime dependencies.

## Scope

### In scope

- Replace Tailwind-based page and fragment styling with Bootstrap 5 classes.
- Keep current page structure and behavior for login, users list, user detail, and todo interactions.
- Install and serve Bootstrap assets locally from project static resources.
- Remove Tailwind runtime dependency and Tailwind-specific class usage from migrated templates.
- Preserve HTMX target IDs and swap semantics during markup updates.

### Out of scope

- Domain/business logic changes unrelated to UI migration.
- Introducing additional UI frameworks.
- SPA/API architecture changes.

## Requirements

- Use `bootstrap-ui-framework` as the project styling skill.
- Do not mix Tailwind and Bootstrap in project baseline or feature implementation.
- Serve Bootstrap/fonts/icons from local static assets (no CDN runtime dependencies).
- Preserve accessibility basics (`label for`, table header `scope`, meaningful button labels).
- Keep server-rendered and HTMX-enhanced interaction model intact.

## Acceptance criteria

- [x] `specs/PRODUCT.md` baseline app skills use `bootstrap-ui-framework` and do not include `tailwindcss`.
- [x] Core templates/fragments render with Bootstrap components and utility classes.
- [x] No Tailwind CDN/script usage or Tailwind-only classes remain in migrated templates.
- [x] Bootstrap CSS/JS (and optional icons/fonts) are loaded from local static asset paths.
- [x] Existing core flows (login, users list/detail, todo add/toggle/delete) behave as before.
- [x] Responsive behavior remains usable on mobile and desktop breakpoints.

## Dependencies

- `.ai/skills/bootstrap-ui-framework/SKILL.md`
- `.ai/guides/local-ui-assets.md`
- `.ai/skills/spring-mvc-thymeleaf-ui-components/SKILL.md`
- `.ai/skills/thymeleaf-templates/SKILL.md`
