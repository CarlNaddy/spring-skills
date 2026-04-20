# Implementation plan: <feature name>

## When this plan is required

Create `plan.md` before implementation when **any** of the following is true. If none apply, you may omit this file and keep decisions in `spec.md`.

- Persistence schema or query shape changes beyond a trivial single-table add
- Authentication, authorization, session, or CSRF/CORS behavior changes
- Shared-tenant isolation or tenant context changes
- Performance-sensitive or high-volume paths (hot loops, N+1 queries, caching)
- Multiple viable architectures or significant product trade-offs
- Migration, rollout, or backward compatibility is non-obvious

## Approach

Describe the chosen implementation strategy.

## Alternatives considered

- Option A:
- Option B:

## Rejected alternatives

Document options that were **not** chosen and why (prevents revisiting the same debate later).

- **Rejected**: … — **Reason**:
- **Rejected**: … — **Reason**:

## Risks

- 

## Database migration plan

- Migration sequence:
- Backfill/compatibility strategy:
- Rollback strategy:

## Environment matrix

- `local` profile behavior:
- `test` profile behavior:
- `prod` profile behavior:
- Verification steps for each environment:

## Validation approach

- Unit tests:
- Integration tests:
- Manual checks:
- Migration validation checks:
- Container/deployment checks:
- Tenant isolation checks (if shared-tenant stack):

