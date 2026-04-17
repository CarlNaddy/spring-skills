# Changelog

All notable framework-level changes are documented in this file.

## 3.4.x.2 - 2026-04-17

### Highlights

- Major production hardening pass across core and integration skills.
- Introduced formal framework versioning policy with release trees by Spring Boot lane.

### Added

- Added `VERSIONING.md` with a lane-based framework version format:
  - `<spring-boot-major>.<spring-boot-minor>.x.<framework-release>`
- Added release-tree policy for parallel Spring Boot lanes (for example `release/3.4.x`, `release/4.0.x`).
- Added lane lifecycle states (`current`, `maintenance`, `eol`) to support multi-lane maintenance over time.
- Added required output contracts in hardened skills to enforce implementation completeness.
- Added testing and verification gates across core and integration skill paths.
- Added Context7-aligned references in hardened skills for version-accurate implementation guidance.
- Added dependency cross-links and quick execution checklists in primary integration skills to reduce architecture drift.

### Changed

- Repositioned repository messaging to emphasize the AI agent skills framework for Java Spring developers.
- Upgraded core backend skills to production-level guidance:
  - `spring-rest-api`
  - `spring-rest-auth-jwt`
  - `spring-mvc-auth-session`
- Upgraded primary integration skills to production-level guidance:
  - `spring-thymeleaf-htmx-integration`
  - `spring-react-integration`
- Normalized section structure and style consistency across upgraded skills.
- Updated `README.md` and `CHANGELOG.md` to document versioning policy and release notes structure.

### Policy

- Standardized Spring configuration guidance to `application.yml` and `application-<profile>.yml`.
- Adopted a no-mixed-format rule in skills (`.properties` and `.yml` should not coexist in the same module).

### Runtime Alignment

- Migrated demo app runtime config from `src/main/resources/application.properties` to `src/main/resources/application.yml` to match policy.

### Metadata

- Updated framework package version in `.ai/framework-package.json`:
  - from `0.1.0` to `3.4.x.2`
