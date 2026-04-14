# Implementation plan: <entity> CRUD

## Approach

- Define/extend repository operations
- Implement service-level business rules
- Add controller endpoints and template updates
- Validate via tests and manual flow checks

## Risks

- Validation drift between create/update
- UI inconsistency between full-page and HTMX interactions

## Validation approach

- Unit tests for service logic
- Integration tests for key controller flows
- Manual checks for happy path + edge cases

