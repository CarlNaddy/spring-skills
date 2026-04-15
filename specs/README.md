# Specs workspace

This folder is the project-owned planning and execution space.

## Structure

- `PRODUCT.md`: one evolving product requirements document
- `features/<id>/spec.md`: what must be built
- `features/<id>/plan.md`: implementation approach (optional)
- `features/<id>/tasks.md`: delivery checklist

## Suggested workflow

1. Update `PRODUCT.md` with current priorities.
2. If stack is not selected, choose one from `.ai/STACKS.md` and record it in `PRODUCT.md` with required skills.
3. Decide one project-wide styling skill (`tailwindcss` or `bootstrap-ui-framework`) and record it in `PRODUCT.md`.
4. Create `features/<id>/` for work that is ready to implement.
5. Write `spec.md` with acceptance criteria first.
6. Add `plan.md` only when there are meaningful design choices.
7. Request developer review and ask for explicit confirmation to proceed.
8. Execute and track completion in `tasks.md`.

