# Spring Boot Full-Stack Skills Framework

[![Specs Consistency](https://github.com/nadobnykh/spring-skills/actions/workflows/specs-consistency.yml/badge.svg)](https://github.com/nadobnykh/spring-skills/actions/workflows/specs-consistency.yml)

A reusable AI framework for building Java web apps with consistent architecture and fast feature delivery.

For fast orientation, read `AGENTS.md` first.

This repository showcases:
- a portable skills system (`.ai/`),
- a specs-driven feature workflow (`specs/`),
- and a reference application used only as an example implementation.

## 60-Second Quickstart

Use this as the primary onboarding path.

### 1) Install the framework

From inside your project folder:

```bash
mkdir -p .ai && curl -o .ai/install-framework.mjs https://raw.githubusercontent.com/CarlNaddy/spring-skills/main/.ai/install-framework.mjs && node .ai/install-framework.mjs
```

Requirements:

- Node.js `18+`

### 2) Send your first kickoff prompt

```text
Initialize this project for a simple CRM for a small sales team.
The app should manage companies, contacts, notes, and follow-up tasks.
First, help me select the best stack from .ai/STACKS.md and the primary UI baseline (tailwindcss or bootstrap-ui-framework), with short trade-offs.
Then create or update specs/PRODUCT.md and initialize the Spring Boot project foundation for the selected stack, including the required baseline files and dependencies.
Optimize for the fastest path to a runnable application, but stop and let me confirm the stack and UI choice before implementing business features.
```

If you are unsure which baseline to choose, start with:

- stack: `spring-thymeleaf-htmx`
- UI baseline: `bootstrap-ui-framework`

### 3) Run the review gate prompt

```text
Show me what changed in specs/PRODUCT.md and the newly created feature spec.
Summarize decisions, assumptions, and open questions.
Wait for my approval before implementing business features.
```

### 4) Run the reference app (this repository)

If you are running this repository's included demo app:

1. Run the app:
   - `mvn spring-boot:run`
2. Open:
   - `http://localhost:8080/login`
3. Sign in:
   - username: `admin`
   - password: `password`

Expected outcome after steps 1-4:

- `specs/PRODUCT.md` is created or updated with selected stack and required skills.
- first feature spec files are scaffolded under `specs/features/<id>/`.
- Spring Boot foundation for the chosen stack is initialized.
- implementation is paused until your explicit approval.
- demo app is reachable at `http://localhost:8080/login` (for this repository).

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
- `spring-thymeleaf-htmx-postgres`
  - SSR baseline + PostgreSQL + Flyway + container runtime
- `spring-react-postgres`
  - SPA baseline + PostgreSQL + Flyway + container runtime
- `spring-thymeleaf-htmx-postgres-shared-tenant`
  - SSR + PostgreSQL shared-database multi-tenancy baseline
- `spring-react-postgres-shared-tenant`
  - SPA + PostgreSQL shared-database multi-tenancy baseline

## Copy-Paste Prompt Examples

For a complete human-oriented prompt reference, see [`.ai/docs/PROMPT-PLAYBOOK.md`](.ai/docs/PROMPT-PLAYBOOK.md).

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

## Use In A New Repository

Use the same onboarding flow in any new project repository:

1. Run the install command from `60-Second Quickstart`.
2. Send one kickoff prompt from `.ai/docs/PROMPT-PLAYBOOK.md`.
3. Review `specs/PRODUCT.md` and the first feature spec before implementation.

Runtime baseline to keep in `specs/PRODUCT.md` unless explicitly changed:

- Java: `17`
- Spring Boot: `3.4.x`
- Version policy: do not upgrade Java/Spring Boot major or minor versions without explicit approval

## Repository Map

- `.ai/` - portable AI framework (`AGENTS.md`, skills, stack catalog)
- `specs/` - product and feature requirements
- `src/` - Spring Boot application code

## Updating The Framework

Re-run `node .ai/install-framework.mjs` to pull framework updates safely. The installer updates framework-managed files, creates `specs/PRODUCT.md` if missing, and skips managed files that were modified locally.

---

If you want to review the process design, start with:

- `docs/product/REPOSITORY-LAYOUT.md`
- `docs/product/PRODUCT-DEVELOPMENT-PLAN.md`
