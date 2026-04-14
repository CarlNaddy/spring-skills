# Feature spec: delete users

## Summary

Allow authenticated users to delete an existing user from the users list in the app.

## Problem

The current user management flow supports creating and listing users, but there is no way to remove incorrect or obsolete records.

## Scope

### In scope

- Add delete action in the users list UI
- Support delete operation in repository/service/controller layers
- Ensure delete works with current server-rendered + HTMX interaction style
- Keep CSRF protection enabled for delete requests

### Out of scope

- Soft delete / audit log
- Bulk delete
- Role-based authorization changes

## Requirements

- Delete action is available for each user row
- Deleting a user updates the list without full-page reload when using HTMX
- Non-HTMX flow still works via normal form submit/redirect
- Attempting to delete a non-existing user must not break the page

## Acceptance criteria

- [x] User list shows a delete action per row
- [x] Clicking delete removes the user and refreshes list state correctly
- [x] HTMX and non-HTMX delete flows both function
- [x] Existing create/list behavior remains intact

## Dependencies

- Selected stack in `specs/PRODUCT.md`
- Existing user management endpoints and templates

