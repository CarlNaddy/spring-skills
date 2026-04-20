# Feature spec: <feature name>

## Summary

One-paragraph description of user-facing outcome.

## Problem

What user or business problem this feature solves.

## Scope

### In scope

- 

### Out of scope

- 

## Requirements

- 

## Acceptance criteria

- [ ] Criterion 1
- [ ] Criterion 2
- [ ] Criterion 3

## Verification (map criteria to evidence)

For each acceptance criterion above, record how you will prove it is met (prefer automated tests).

| Acceptance criterion | Evidence type (test / manual / metric) | Location or steps |
| --- | --- | --- |
| Criterion 1 |  |  |
| Criterion 2 |  |  |
| Criterion 3 |  |  |

## Definition of done

Checklist for closing the feature (keep aligned with `tasks.md`).

- [ ] All acceptance criteria checked off with evidence in the verification table
- [ ] Automated tests added or updated for non-trivial behavior (or explicit rationale in spec if not testable)
- [ ] No new linter/compiler warnings introduced without justification
- [ ] Security-sensitive paths reviewed when applicable (authz, uploads, secrets, tenant boundaries)
- [ ] Rollout/backward compatibility satisfied per **Staged rollout** and **Migration strategy** sections

## Assumptions and invariants

- **Assumptions** (validate or correct before shipping):
  - 
- **Invariants** (must remain true; implementation must not violate):
  - 

## Threat and abuse notes (when applicable)

Complete this subsection when the feature touches **authentication, authorization, sessions, uploads, payments, webhooks, or tenant isolation**.

- **Assets at risk**:
- **Threats / abuse cases**:
- **Mitigations** (code, config, tests):
- **Residual risk**:

## Dependencies

- 

## Data model impact

- Persistence change type: (none | new table | altered table | index/constraint update | data backfill)
- Affected entities/repositories:
- Tenant impact (if applicable):

## Migration strategy

- [ ] Flyway migration required
- Migration files:
- Backward compatibility and rollout notes:

## Runtime config and secrets

- Profiles affected (`local` / `test` / `prod`):
- New environment variables/secrets:
- Datasource behavior changes (H2/PostgreSQL):

## Deployment considerations

- Container/runtime impact:
- Health/readiness impact:
- Rollback strategy:

## Staged rollout and release safety

- **Release strategy** (big-bang | incremental | behind flag | dark launch):
- **Backward compatibility** (clients, APIs if any, data on disk):
- **Feature flag or configuration key** (if used):
- **Monitoring** (what to watch after deploy):
- **Rollback or disable path**:

## Tenant isolation (shared-tenant stacks only)

- Tenant context boundary:
- Isolation guarantees required:
- [ ] Cross-tenant read isolation validated
- [ ] Cross-tenant write isolation validated

