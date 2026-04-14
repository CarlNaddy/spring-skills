# Implementation plan: user todos

## Approach

Use the existing user feature layers (controller -> service -> repository) and extend todo support scoped by user ID. Render todos in the existing detail page and update only the todo section with HTMX for add/delete/mark-done operations.

## Alternatives considered

- Option A: Separate todo controller and template files
- Option B: Extend current user controller/detail template (chosen for simplicity and consistency)

## Risks

- In-memory data coupling between users and todos
- Ensuring HTMX and non-HTMX flows stay consistent
- Preventing inconsistent state transitions when marking already-done todos

## Validation approach

- Unit tests: N/A (project currently relies on integration-style controller tests)
- Integration tests: add coverage for todo add/delete/mark-done + detail rendering
- Manual checks: user detail loads, add works, delete works, mark done works, existing flows unchanged

