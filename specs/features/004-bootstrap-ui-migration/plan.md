# Implementation plan: Bootstrap UI migration

## Approach

- Phase 1: Establish local Bootstrap assets and update shared layout includes.
- Phase 2: Migrate highest-traffic templates/fragments (login, users list/detail, todo UI) from Tailwind to Bootstrap classes.
- Phase 3: Remove Tailwind includes/usages and verify no CDN references.
- Phase 4: Regression-check HTMX targets/swaps and responsive behavior.

## Alternatives considered

- Option A: Incremental page-by-page migration with behavior checks after each slice (preferred).
- Option B: Single large rewrite (faster initial editing, higher regression risk).

## Risks

- HTMX target regressions if wrapper IDs or swap containers change.
- Visual regressions on mobile breakpoints.
- Partial migration leaving mixed class conventions.

## Validation approach

- Unit tests: N/A unless backend logic changes.
- Integration tests: Keep/extend MockMvc tests for critical views and HTMX flows.
- Manual checks:
  - Login page and authenticated navigation
  - Users list/detail rendering
  - Todo add/toggle/delete interactions
  - Mobile/table responsiveness
  - Verify no CDN URLs in rendered page source
