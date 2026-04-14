# Feature spec: user todos

## Summary

Allow authenticated users to manage a todo list for each user from the existing user detail page.

## Problem

The detail page currently shows only profile data and does not support tracking user-specific tasks, which limits usefulness for day-to-day follow-up.

## Scope

### In scope

- Display a todo list on the user detail page for the selected user
- Add a todo item for a selected user
- Delete a todo item for a selected user
- Mark a todo item as done for a selected user
- Keep behavior aligned with current SSR + HTMX interaction style

### Out of scope

- Editing todo text
- Bulk todo operations across multiple users

## Requirements

- User detail page renders todo section below user information
- Adding a todo updates the todo list without full-page reload for HTMX requests
- Deleting a todo updates the todo list without full-page reload for HTMX requests
- Marking a todo as done updates that todo state without full-page reload for HTMX requests
- Non-HTMX form submit/redirect flow also works for add/delete
- Missing user ID returns not-found behavior consistent with existing detail flow

## Acceptance criteria

- [x] Detail page includes todo list section for a user
- [x] Add todo creates a new item for that user and renders it
- [x] Delete todo removes the target item for that user
- [x] Mark todo as done updates the item state for that user
- [x] Existing user list/detail/delete flows remain functional

## Dependencies

- Selected stack in `specs/PRODUCT.md`
- Existing user detail route and templates

