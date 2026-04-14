# Feature spec: user detail view

## Summary

Allow authenticated users to click a row in the users list and open a dedicated detail view that displays all currently available information for that user.

## Problem

The current user management experience supports listing users, but there is no direct way to inspect a single user's full details from the list view.

## Scope

### In scope

- Add a row-click or equivalent primary action from the users list to open user details
- Add backend route/controller flow to render a server-side user detail page
- Display all available user fields that already exist in the current model/repository
- Keep behavior aligned with SSR + HTMX stack constraints

### Out of scope

- Editing user details
- Introducing new user attributes not already available in current data model
- Role/permission model changes

## Requirements

- Each user row in list view provides a clear path to open the user detail view
- User detail page renders all available fields for that user
- Missing/non-existing user ID is handled gracefully (not found page or redirect with message)
- Non-authenticated access remains protected by existing auth/session rules

## Acceptance criteria

- [x] Clicking a user row (or its primary action) opens that user's detail view
- [x] Detail view displays all currently available user information
- [x] Existing list/create/delete behaviors remain functional
- [x] Invalid user ID does not break the app and returns expected not-found handling

## Dependencies

- Selected stack in `specs/PRODUCT.md`
- Existing user list flow and user data model

