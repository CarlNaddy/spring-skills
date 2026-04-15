# Feature spec: <feature name> Bootstrap UI

## Summary

Implement or refresh `<feature name>` UI using Bootstrap 5 patterns while preserving SSR + HTMX behavior.

## Problem

The current UI lacks a consistent Bootstrap-based structure for layout, responsiveness, and form/table interactions.

## Scope

### In scope

- Apply Bootstrap layout primitives (`container`, grid, spacing utilities)
- Standardize feature components (`card`, `btn`, `form-control`, `table`)
- Improve responsive behavior (`table-responsive`, `d-none d-md-table-cell` where appropriate)
- Keep HTMX targets stable during UI updates
- Preserve existing feature behavior and validation semantics

### Out of scope

- Rewriting backend/domain logic unrelated to UI rendering
- Mixing Tailwind and Bootstrap in the same feature
- SPA migration or JSON UI APIs

## Requirements

- Use `bootstrap-ui-framework` as the primary styling skill for this feature.
- Keep rendering server-driven (Thymeleaf templates + fragments).
- Maintain accessibility basics (`label for`, `scope="col"`, ARIA labels on toggles/buttons).
- Ensure fragment swaps retain required Bootstrap wrappers/chrome.
- Serve Bootstrap, fonts, and related static UI assets from local project files (no CDN by default).

## Acceptance criteria

- [ ] Feature pages/fragments use consistent Bootstrap 5 components
- [ ] Responsive behavior works on narrow screens without layout breakage
- [ ] HTMX interactions still target/update the correct DOM regions
- [ ] Validation states/messages render with Bootstrap feedback patterns
- [ ] No Tailwind classes are introduced in this feature unless explicitly approved
- [ ] Bootstrap/fonts/icons are loaded from local static assets, not CDN URLs

## Dependencies

- `.ai/skills/bootstrap-ui-framework/SKILL.md`
- `.ai/skills/spring-mvc-thymeleaf-ui-components/SKILL.md`
- `.ai/skills/thymeleaf-templates/SKILL.md`
