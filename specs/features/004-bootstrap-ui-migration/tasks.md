# Tasks: Bootstrap UI migration

## Todo

- [x] Confirm migration scope and non-goals in `spec.md`
- [x] Update `specs/PRODUCT.md` baseline styling skill to `bootstrap-ui-framework`
- [x] Set up local Bootstrap assets per `.ai/guides/local-ui-assets.md`
- [x] Migrate shared layout and common fragments from Tailwind to Bootstrap
- [x] Migrate page templates (`login`, `users`, `user detail`) to Bootstrap patterns
- [x] Validate HTMX target wrappers/IDs and interaction behavior after migration
- [x] Remove remaining Tailwind references/classes from migrated surface
- [x] Validate acceptance criteria and summarize outcomes

## Outcome summary

- Migrated UI templates/components from Tailwind classes to Bootstrap 5 classes.
- Replaced CDN asset loading with local project assets under `src/main/resources/static/vendor`.
- Preserved existing HTMX targets/flows for users and todos.
- Validation complete: `mvn test` passed and `scripts/validate-specs.sh` passed.

## Done

- [x] Feature shipped
