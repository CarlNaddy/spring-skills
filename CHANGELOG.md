# Changelog

All notable framework-level changes are documented in this file.

## 2026-04-17 - Versioning System Adopted

### Added

- Added `VERSIONING.md` with a lane-based framework version format:
  - `<spring-boot-major>.<spring-boot-minor>.x.<framework-release>`
- Added release-tree policy for parallel Spring Boot lanes (for example `release/3.4.x`, `release/4.0.x`).
- Added lane lifecycle states (`current`, `maintenance`, `eol`) to support multi-lane maintenance over time.

### Changed

- Updated `README.md` with a `Versioning And Release Trees` section and active lane status.

## 2026-04-17 - Framework Hardening Wave

### Changed

- Repositioned repository messaging to emphasize the AI agent skills framework for Java Spring developers.
- Upgraded core backend skills to production-level guidance:
  - `spring-rest-api`
  - `spring-rest-auth-jwt`
  - `spring-mvc-auth-session`
- Upgraded primary integration skills to production-level guidance:
  - `spring-thymeleaf-htmx-integration`
  - `spring-react-integration`

### Added

- Required output contracts in hardened skills to enforce implementation completeness.
- Testing and verification gates across core and integration skill paths.
- Context7-aligned reference links in hardened skills for version-accurate implementation guidance.
- Dependency cross-links and quick execution checklists in primary integration skills to reduce architecture drift.

### Policy

- Standardized Spring configuration guidance to `application.yml` and `application-<profile>.yml`.
- Adopted a no-mixed-format rule in skills (`.properties` and `.yml` should not coexist in the same module).

### Runtime Alignment

- Migrated demo app runtime config from `src/main/resources/application.properties` to `src/main/resources/application.yml` to match policy.
