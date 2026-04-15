# Implementation plan: <feature name> Bootstrap UI

## Approach

- Identify target pages/fragments and current styling inconsistencies.
- Refactor templates to Bootstrap-first component structure.
- Add/adjust responsive utilities and wrappers for mobile/table usability.
- Verify HTMX swap targets still align with updated markup wrappers.

## Alternatives considered

- Option A: Incremental Bootstrap component replacement per fragment (preferred).
- Option B: Full-page rewrite in one pass (higher risk, harder to validate).

## Risks

- HTMX swap targets can break if IDs/wrappers move unexpectedly.
- Validation feedback can regress if class mapping is inconsistent.
- Over-customization can drift away from Bootstrap primitives.

## Validation approach

- Unit tests: N/A unless backend changes are introduced.
- Integration tests: Verify key server-rendered routes still return expected views/fragments.
- Manual checks:
  - Desktop + mobile view sanity check
  - Form validation feedback rendering
  - HTMX create/update/delete flows still behave correctly
