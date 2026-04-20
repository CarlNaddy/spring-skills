# Tasks: <feature name>

## Incremental delivery (vertical slices)

Prefer one acceptance criterion (or a tight group) per slice. Check off as you complete each slice.

- [ ] Slice 1: `<short label>` — acceptance: `...`
- [ ] Slice 2: `<short label>` — acceptance: `...`
- [ ] Slice 3: `<short label>` — acceptance: `...`

## Todo

- [ ] Clarify/confirm feature requirements in `spec.md`
- [ ] Fill **Verification** table and **Definition of done** in `spec.md` before implementation work
- [ ] Create `plan.md` when planning triggers apply (see `.ai/AGENTS.md` section 3.4)
- [ ] Implement backend changes
- [ ] Implement template/UI changes
- [ ] Add/update persistence model and repository changes (if needed)
- [ ] Add/update Flyway migrations and validate migration order
- [ ] Update profile-based configuration (`local`/`test`/`prod`) and env var contract
- [ ] Validate local runtime path (direct run or Docker Compose parity path)
- [ ] Validate deployment/runtime contract (container startup + health checks)
- [ ] Validate tenant isolation paths (if shared-tenant stack selected)
- [ ] Add or update tests mapped in `spec.md` verification table
- [ ] Validate acceptance criteria with recorded evidence
- [ ] Mark done and summarize outcomes

## Definition of done (task-level)

Mirror `spec.md` and ensure everything is true before closing the feature.

- [ ] Verification table in `spec.md` is complete and accurate
- [ ] `mvn verify` (or project equivalent) passes locally
- [ ] Security/threat notes completed when the feature touches auth, uploads, payments, or tenant boundaries
- [ ] Staged rollout and rollback/disable path understood and documented if deploy-sensitive

## Done

- [ ] Feature shipped

