# Implementation plan: delete users

## Approach

- Extend user repository interface with `deleteById`.
- Implement deletion in `InMemoryUserRepository`.
- Add service method for delete operation.
- Add controller endpoint for delete request.
- Render delete action in `users/list.html` with CSRF-protected form.
- Return fragment/full page depending on HTMX request pattern used in current app.

## Alternatives considered

- Option A: JavaScript `fetch` delete calls (rejected; conflicts with current HTML-first approach)
- Option B: Pure full-page form submits only (works, but less UX consistency with current HTMX usage)

## Risks

- Inconsistent DOM updates if delete endpoint returns wrong fragment
- CSRF token not included in delete forms
- Regressions in existing create/list flow

## Validation approach

- Unit tests: service/repository delete behavior
- Integration tests: authenticated delete route behavior
- Manual checks: delete via UI, list refresh, empty-state behavior

