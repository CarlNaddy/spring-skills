# Repository layout (simplified)

This repository uses a simple split:

- `ai/` is a portable skill pack (copied/imported into projects)
- `specs/` is project-owned product/spec execution workspace

---

## 1. Directory structure

```text
<project-root>/
  ai/                        # portable package: AGENT + skills only
  specs/
    PRODUCT.md               # one evolving product requirements document
    README.md                # process and status index
    features/
      <feature-id>/
        spec.md              # feature requirements + acceptance criteria
        plan.md              # implementation approach (optional for small work)
        tasks.md             # actionable checklist (source of execution truth)
```

---

## 2. What goes where

| Path | Purpose |
|------|---------|
| `ai/` | Reusable agent package; no project-specific product docs |
| `specs/PRODUCT.md` | Single evolving PRD for this product |
| `specs/features/*/spec.md` | Exact behavior and acceptance criteria for one feature |
| `specs/features/*/plan.md` | How to implement (only when useful) |
| `specs/features/*/tasks.md` | Step-by-step delivery checklist |

---

## 3. Working rules

1. Keep one evolving product doc in `specs/PRODUCT.md`.
2. Add a feature folder only when a change is ready to execute.
3. Keep `spec.md` testable and concise.
4. Use `plan.md` only when there are meaningful trade-offs.
5. Track real progress in `tasks.md` checkboxes.

---

## 4. Precedence

For implementation decisions in a project:

1. Current feature `specs/features/<id>/spec.md` + accepted scope in `specs/PRODUCT.md`
2. Relevant `ai/skills/**/SKILL.md`
3. `ai/AGENT.md` defaults and constraints

If product requirements and skill constraints conflict, update requirements or architecture explicitly before coding.

---

## 5. Why this layout

- Very low process overhead
- Clear file ownership (portable `ai/` vs project `specs/`)
- Easy review in pull requests
- Scales from one evolving PRD to many feature folders without reorganization
