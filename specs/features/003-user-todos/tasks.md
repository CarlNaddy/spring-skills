# Tasks: user todos

## Todo

- [x] Clarify/confirm feature requirements in `spec.md`
- [x] Implement backend changes
- [x] Implement template/UI changes
- [x] Add or update tests
- [x] Validate acceptance criteria
- [x] Mark done and summarize outcomes
- [x] Extend backend todo model/logic to support done state
- [x] Add controller route/action to mark todo as done
- [x] Update todo UI to show done state and provide "mark done" action
- [x] Add/update tests for mark-done flow (HTMX + non-HTMX)
- [x] Re-validate acceptance criteria and update outcome summary

## Outcome summary

- Added per-user todo support through repository, service, and controller layers.
- Extended user detail view with todo list, add form, and delete actions.
- Added done-state support with a mark-done action and done badge/strike-through rendering.
- Preserved SSR + HTMX behavior with fragment updates for todo interactions.
- Verified via automated tests (`mvn test`).

## Done

- [x] Feature shipped

