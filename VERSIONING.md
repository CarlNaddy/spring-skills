# Framework Versioning Policy

This project uses a framework-specific versioning scheme that is aligned to supported Spring Boot lines.

## Version Format

Framework releases use:

- `<spring-boot-major>.<spring-boot-minor>.x.<framework-release>`

Examples:

- `3.4.x.1`
- `3.4.x.10`
- `4.0.x.1`
- `4.1.x.3`

Meaning:

- `<spring-boot-major>.<spring-boot-minor>`: the Spring Boot support lane.
- `x`: any compatible patch version inside that Spring Boot minor line.
- `<framework-release>`: framework release sequence inside the lane.

## Why This Format

- Clearly distinguishes framework version from raw Spring Boot version.
- Makes supported Spring Boot lane visible at a glance.
- Supports multiple active lanes in parallel.

## Release Trees (Support Lanes)

Each supported Spring Boot lane has its own release tree.

Recommended branch model:

- `release/3.4.x` for framework versions `3.4.x.N`
- `release/4.0.x` for framework versions `4.0.x.N`
- `release/4.1.x` for framework versions `4.1.x.N` (when introduced)

Recommended tag model:

- `v3.4.x.1`, `v3.4.x.2`, ...
- `v4.0.x.1`, `v4.0.x.2`, ...

## Lane Lifecycle

Each lane should be marked as one of:

- `current`: default lane for new users.
- `maintenance`: security/fixes only.
- `eol`: no further releases.

Document current lane status in `README.md`.

## Release Rules

- Framework-only improvements in a lane increment `<framework-release>`.
- Breaking framework behavior can still ship in-lane, but MUST include explicit migration notes.
- Spring Boot minor line change creates a new lane (for example `3.4.x` -> `4.0.x`).
- Do not publish ambiguous versions that can be read as plain Spring Boot versions.

## Compatibility Declaration

Each release SHOULD declare:

- framework version (`3.4.x.N` style),
- supported Spring Boot line (`3.4.x`, `4.0.x`, etc.),
- lane status (`current`, `maintenance`, `eol`).

Use `CHANGELOG.md` for release notes and migration guidance.
Use `RELEASES.md` for release execution and announcement templates.
