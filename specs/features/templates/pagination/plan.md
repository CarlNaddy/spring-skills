# Implementation plan: pagination

## Approach

- Add paginated query methods in repository/service
- Expose page parameters through controller routes
- Render pagination controls in templates
- Preserve compatibility with HTMX partial updates when applicable

## Risks

- Off-by-one page boundary bugs
- Inconsistent page state after mutations (create/delete)

## Validation approach

- Tests for boundary pages
- Manual checks for page transitions and empty states

