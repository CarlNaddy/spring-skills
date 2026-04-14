# Product development plan (simplified)

This plan uses one project-owned `specs/` workspace and keeps `ai/` portable.

See [REPOSITORY-LAYOUT.md](REPOSITORY-LAYOUT.md).

---

## 1. Goals

- Keep one evolving product requirements file.
- Keep each feature self-contained and executable.
- Keep process light: requirement, plan (optional), and tasks.
- Keep `ai/` reusable and free of project-specific product docs.

---

## 2. Operating model

Use:

- `specs/PRODUCT.md` for overall product direction.
- `specs/features/<id>/spec.md` for feature requirements and acceptance criteria.
- `specs/features/<id>/plan.md` only if design trade-offs need written decisions.
- `specs/features/<id>/tasks.md` as execution checklist.

No separate PRD archive is required unless complexity grows.

---

## 3. Workflow

1. Update `specs/PRODUCT.md` with scope and priority.
2. Create a feature folder under `specs/features/`.
3. Write `spec.md` first (must be testable and clear).
4. Add `plan.md` only when needed.
5. Break work into `tasks.md` and execute.
6. Verify feature against acceptance criteria in `spec.md`.
7. Mark tasks done and summarize outcomes.

---

## 4. Conventions

- Feature folder naming: `NNN-short-name` (example: `001-auth-hardening`).
- Keep files concise and decision-focused.
- Keep task items small enough to complete in one session.
- Prefer checklists with concrete outcomes over narrative updates.

---

## 5. Minimal file contract per feature

Required:

- `spec.md`
- `tasks.md`

Optional:

- `plan.md`

---

## 6. Adoption steps

1. Create `specs/README.md`, `specs/PRODUCT.md`, and `specs/features/`.
2. Add a `specs/features/TEMPLATE/` starter set (`spec.md`, `plan.md`, `tasks.md`).
3. Start new work only from `specs/features/<id>/`.
4. Keep old `docs/product/` files only as migration notes.
