# Spring Boot Full-Stack Skills Framework

[![Specs Consistency](https://github.com/nadobnykh/spring-skills/actions/workflows/specs-consistency.yml/badge.svg)](https://github.com/nadobnykh/spring-skills/actions/workflows/specs-consistency.yml)

A reusable AI framework for building Java web apps with consistent architecture and fast feature delivery.

For fast orientation, read `AGENTS.md` first.

This repository showcases:
- a portable skills system (`.ai/`),
- a specs-driven feature workflow (`specs/`),
- and a reference application used only as an example implementation.

## Demo

Add your visuals here to make first-time visitors understand value quickly:

- `docs/assets/feature-workflow.gif` (prompt -> scaffold -> implement)
- `docs/assets/reference-app-users.png` (running app screenshot)

## Skills First

The core value is the AI skills framework:

- `.ai/AGENTS.md` defines workflow and guardrails.
- `.ai/skills/**` contains atomic + integration skills.
- `.ai/STACKS.md` is the stack catalog the agent uses for selection.
- The agent must record selected stack and required skills in `specs/PRODUCT.md` before implementation.
- Spring Boot stacks include `spring-boot-devtools` by default (`pom.xml` with `<optional>true</optional>` for development-only behavior).

## Why This Framework

Compared to ad-hoc prompting:

- deterministic stack selection (no accidental tech drift),
- consistent feature scaffolding (`spec.md`, `tasks.md`, optional `plan.md`),
- explicit skill constraints and conflict handling,
- faster onboarding for teams using shared conventions.

## Supported Java/Spring Stacks

Defined in `.ai/STACKS.md`:

- `spring-thymeleaf-htmx`
  - Spring MVC (SSR), Thymeleaf templates, HTMX interactivity
  - HTML-first, no JSON UI APIs
- `spring-react`
  - Spring REST API + React SPA
  - JSON-first client/server separation

## Copy-Paste Prompt Examples

```text
Let's create feature user-search.
```

```text
Create a new feature for user role filters and scaffold all spec files.
```

```text
Implement specs/features/user-search/spec.md end-to-end and validate tests.
```

### Template-specific prompts (quick wins)

```text
Create feature project-crud using the CRUD template in specs/features/templates/crud.
```

```text
Create feature auth-hardening using specs/features/templates/auth-hardening and scaffold spec/tasks/plan.
```

```text
Create feature user-pagination using specs/features/templates/pagination, then implement the first slice.
```

```text
Create feature admin-bootstrap-refresh using specs/features/templates/bootstrap-ui and scaffold spec/tasks/plan.
```

## Example Implementation

The included Spring app is a demo vehicle that shows the framework in action:

- Spring MVC + Thymeleaf + HTMX
- Session authentication
- User management flows
- Specs-first feature delivery

## Prompt A New Feature Fast

Use prompts like:

- "Let's create feature `user-search`"
- "Create a new feature for exporting users"

Expected flow:

1. Agent checks selected stack (or asks to choose from `.ai/STACKS.md`).
2. Agent scaffolds `specs/features/<id>/` (commonly `NNN-short-title`).
3. Agent creates:
   - `spec.md` (requirements + acceptance criteria)
   - `tasks.md` (execution checklist)
   - `plan.md` (optional, when design trade-offs exist)
4. Implementation follows the selected skill set.

## Specs-Driven Workflow

1. Set product direction in `specs/PRODUCT.md`.
2. Create a feature folder: `specs/features/<id>/`.
3. Use template files from `specs/features/TEMPLATE/`.
4. Optionally start from specialized templates in `specs/features/templates/` (`crud`, `auth-hardening`, `pagination`, `bootstrap-ui`).
5. Implement and verify against acceptance criteria.

## Start From Scratch

You can use this framework without the example app:

### Prerequisites before starting a new project

Set and record runtime/framework versions first (before implementation):

- Java version target (default in this repo: `17`)
- Spring Boot target line (default in this repo: `3.4.x`; current parent `3.4.4`)
- Version policy: no Java/Spring Boot major or minor upgrades unless explicitly approved

Record these in `specs/PRODUCT.md` and keep generated code compatible with them.

1. Keep only the `.ai/` folder.
2. Create a new project codebase (Spring Boot, or another stack from `.ai/STACKS.md`).
3. Prompt your product idea and let the agent scaffold `specs/PRODUCT.md` and feature files automatically.
4. Review the generated specs, confirm the stack from `.ai/STACKS.md`, and iterate with follow-up prompts.

## Check It Out Fast

1. Run the app:
   - `mvn spring-boot:run`
2. Open:
   - `http://localhost:8080/login`
3. Sign in:
   - username: `admin`
   - password: `password`

## Repository Map

- `.ai/` - portable AI framework (`AGENTS.md`, skills, stack catalog)
- `specs/` - product and feature requirements
- `src/` - Spring Boot application code

## Roadmap

- [x] Skills-first workflow with stack selection gate
- [x] Specs-based feature scaffolding templates
- [x] Reference app with SSR + HTMX example
- [ ] Demo visuals (GIF + screenshots)
- [x] Additional feature templates (CRUD, auth hardening, pagination, bootstrap-ui)
- [x] CI checks for specs and template consistency

---

If you want to review the process design, start with:

- `docs/product/REPOSITORY-LAYOUT.md`
- `docs/product/PRODUCT-DEVELOPMENT-PLAN.md`
