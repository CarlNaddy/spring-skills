# Prompt Playbook (Human Guide)

Use this file like a command reference, but with prompts.

Copy a prompt, replace placeholders, and send it to the agent.

---

## 1) New Project Kickoff

### Beginner path (first 3 prompts)

Use this sequence if you are new to the framework and want the fastest path to a runnable baseline.

1. Send one kickoff prompt from `Starter product ideas`.
2. Send the `Review after kickoff` prompt.
3. Approve and continue with feature scaffolding or implementation.

Recommended beginner default when unsure:

- stack: `spring-thymeleaf-htmx`
- UI baseline: `bootstrap-ui-framework`

### Starter product ideas

Use one of these when you want a realistic example product that is still small enough to bootstrap quickly.

#### Simple CRM

```text
Initialize this project for a simple CRM for a small sales team.
The app should manage companies, contacts, notes, and follow-up tasks.
First, help me select the best stack from .ai/STACKS.md and the primary UI baseline (tailwindcss or bootstrap-ui-framework), with short trade-offs.
Then create or update specs/PRODUCT.md and initialize the Spring Boot project foundation for the selected stack, including the required baseline files and dependencies.
Optimize for the fastest path to a runnable application, but stop and let me confirm the stack and UI choice before implementing business features.
```

#### Team Task Tracker

```text
Initialize this project for a team task tracker.
The app should support projects, tasks, status columns, assignees, due dates, and simple dashboard views.
First, help me select the best stack from .ai/STACKS.md and the primary UI baseline (tailwindcss or bootstrap-ui-framework), with short trade-offs.
Then create or update specs/PRODUCT.md and initialize the Spring Boot project foundation for the selected stack, including the required baseline files and dependencies.
Optimize for the fastest path to a runnable application, but stop and let me confirm the stack and UI choice before implementing business features.
```

#### Inventory Manager

```text
Initialize this project for a lightweight inventory manager.
The app should manage products, stock levels, suppliers, and low-stock alerts.
First, help me select the best stack from .ai/STACKS.md and the primary UI baseline (tailwindcss or bootstrap-ui-framework), with short trade-offs.
Then create or update specs/PRODUCT.md and initialize the Spring Boot project foundation for the selected stack, including the required baseline files and dependencies.
Optimize for the fastest path to a runnable application, but stop and let me confirm the stack and UI choice before implementing business features.
```

#### Appointment Scheduler

```text
Initialize this project for a simple appointment scheduler.
The app should manage customers, appointment types, bookings, and daily schedule views.
First, help me select the best stack from .ai/STACKS.md and the primary UI baseline (tailwindcss or bootstrap-ui-framework), with short trade-offs.
Then create or update specs/PRODUCT.md and initialize the Spring Boot project foundation for the selected stack, including the required baseline files and dependencies.
Optimize for the fastest path to a runnable application, but stop and let me confirm the stack and UI choice before implementing business features.
```

### Review after kickoff (recommended)

After running one kickoff prompt, ask the agent to pause for human review:

```text
Show me what changed in specs/PRODUCT.md and the newly created feature spec.
Summarize decisions, assumptions, and open questions.
Wait for my approval before implementing business features.
```

### Ready-to-implement checklist

Before asking for feature implementation, confirm all of these are true:

- selected stack is recorded in `specs/PRODUCT.md`
- primary UI baseline is recorded in `specs/PRODUCT.md`
- newly created feature spec has been reviewed by a human
- open questions and assumptions were resolved or accepted
- explicit approval to implement was given in chat

### Choose stack explicitly

```text
Use stack <stack-id> from .ai/STACKS.md and record it in specs/PRODUCT.md with required skills.
Then scaffold the first feature specs only (no code).
```

Developer-selectable stack IDs:

- `spring-thymeleaf-htmx`
- `spring-react`
- `spring-thymeleaf-htmx-postgres`
- `spring-react-postgres`
- `spring-thymeleaf-htmx-postgres-shared-tenant`
- `spring-react-postgres-shared-tenant`

Selection note:

- Developer selects one stack from this list.
- The stack maps to a primary integration skill.
- Secondary integration skills and atomic skills are auto-resolved by the agent from stack requirements.

### Choose UI baseline explicitly (optional but recommended)

Use this when you want framework-level visual consistency from the start.
Works for both Thymeleaf and React stacks.

```text
Set the primary UI skill for this project to <ui-skill> and record it in specs/PRODUCT.md.
Allowed values: tailwindcss or bootstrap-ui-framework.
Ensure only one primary UI skill is active for the project baseline.
```

---

## 2) Feature Scaffolding

### Create a new feature (default template)

```text
Create feature <NNN-short-title>.
Scaffold specs/features/<NNN-short-title>/spec.md, tasks.md, and plan.md if needed.
Update specs/PRODUCT.md with feature reference and status.
Do not implement until I approve.
```

### Create from specialized template

```text
Create feature <NNN-short-title> using specs/features/templates/<template-name>.
Scaffold spec/tasks/plan and update specs/PRODUCT.md.
Do not implement until I approve.
```

Template names you can use now:

- `crud`
- `auth-hardening`
- `pagination`
- `bootstrap-ui`

If you need a template that does not exist yet, prompt the agent to scaffold from `specs/features/TEMPLATE/` with your desired structure.

---

## 3) Spec Review and Implementation

### Ask for spec review readiness

```text
Review specs/features/<feature-id>/spec.md and tasks.md for clarity, risks, and missing acceptance criteria.
Suggest edits only; do not implement yet.
```

### Approve and implement

```text
Approved. Implement specs/features/<feature-id>/spec.md end-to-end.
Follow .ai/AGENTS.md gates, update tasks.md statuses, add/update tests, and summarize acceptance criteria results.
```

---

## 4) Persistence and Database Prompts

### Add persistence to a feature

```text
Implement persistence for specs/features/<feature-id>/spec.md using the selected stack skills.
Add or update JPA entities/repositories/services, create Flyway migrations, and verify profile behavior for local/test/prod.
```

### Enforce migration discipline

```text
For this feature, enforce Flyway-first schema changes.
Create migration files with proper versioned naming and validate migration flow.
Do not use runtime schema auto-update in production-like profiles.
```

### Verify H2 + PostgreSQL profile matrix

```text
Audit datasource/profile setup for this project.
Ensure H2 is limited to local/test and PostgreSQL is used for production-like profiles with externalized secrets.
Propose and apply fixes if needed.
```

---

## 5) Docker and Deployment Prompts

### Local container runtime

```text
Set up or update Docker Compose for local app + PostgreSQL runtime.
Add healthchecks, startup ordering, named volumes, and document the run commands.
```

### VPS deployment baseline

```text
Prepare Docker-based VPS deployment baseline for this project.
Use immutable image flow, environment-driven config, health checks, and a rollback/runbook checklist.
```

### Deployment readiness check

```text
Run a deployment-readiness review for this feature:
configuration contract, health endpoints, migration safety, rollback path, and operational risks.
Return findings and required fixes.
```

---

## 6) Shared-Tenant (SaaS) Prompts

### Add shared-database tenant isolation

```text
Implement shared-database multi-tenancy for specs/features/<feature-id>/spec.md.
Ensure tenant context propagation, tenant-safe repository queries, tenant-scoped constraints/indexes, and isolation tests for read/write paths.
```

### Tenant safety audit

```text
Audit tenant isolation across this module.
List any query/service path that could leak cross-tenant data and apply fixes with tests.
```

---

## 7) UI and Stack-Specific Prompts

### Set or switch UI baseline

```text
Set the project primary UI skill to <ui-skill> in specs/PRODUCT.md and align upcoming implementation to it.
Allowed values: tailwindcss or bootstrap-ui-framework.
Do not mix both in the same project baseline.
```

### Audit UI baseline consistency

```text
Audit this project for UI baseline consistency with specs/PRODUCT.md.
Flag any mixed Tailwind/Bootstrap usage and propose a migration-safe cleanup plan.
```

### Thymeleaf + HTMX

```text
Implement this feature using SSR + Thymeleaf + HTMX fragment updates.
Keep progressive enhancement, CSRF-safe HTMX requests, and server-rendered HTML responses.
```

### React + REST

```text
Implement this feature using Spring REST APIs and React frontend.
Keep API contracts explicit, validation consistent, and do not mix Thymeleaf rendering in this stack.
```

---

## 8) Quality, Review, and Release Prompts

### Code review pass

```text
Review all changes for this feature with a bug-risk-first mindset.
List findings by severity, then propose minimal fixes and missing tests.
```

### Test and acceptance verification

```text
Run and summarize verification for specs/features/<feature-id>/spec.md:
automated tests, manual checks, and acceptance criteria pass/fail with evidence.
```

### Prepare PR summary

```text
Draft a PR-ready summary for this feature:
what changed, why, risk areas, and a concrete test plan checklist.
```

---

## 9) Prompting Tips

- Keep prompts explicit about phase: "spec only", "implement now", or "review only".
- Always reference concrete files (`specs/features/<id>/spec.md`) when possible.
- Ask for acceptance criteria evidence, not only "done" statements.
- For risky changes, ask for a "findings-first review" before merge.

---

## 10) Upgrading Stacks

Use stack upgrades when the project baseline changes significantly, such as adding persistence, PostgreSQL, Flyway, container runtime, or shared-database multitenancy.

Do not treat these as ad-hoc skill additions. Upgrade the selected stack in `specs/PRODUCT.md` first, then review the updated constraints before implementation.

### Upgrade from SSR base stack to PostgreSQL baseline

```text
Upgrade the selected stack from spring-thymeleaf-htmx to spring-thymeleaf-htmx-postgres.
Update specs/PRODUCT.md, list the newly added required skills and constraints, identify affected feature specs, and stop for review before implementation.
```

### Upgrade from React base stack to PostgreSQL baseline

```text
Upgrade the selected stack from spring-react to spring-react-postgres.
Update specs/PRODUCT.md, list the newly added required skills and constraints, identify affected feature specs, and stop for review before implementation.
```

### Upgrade from PostgreSQL stack to shared-tenant stack

```text
Upgrade the selected stack from <postgres-stack> to <shared-tenant-stack>.
Update specs/PRODUCT.md, explain the newly added tenant-related constraints, add tenant-isolation acceptance criteria guidance, and stop for review before implementation.
```

Examples:

- `spring-thymeleaf-htmx-postgres` -> `spring-thymeleaf-htmx-postgres-shared-tenant`
- `spring-react-postgres` -> `spring-react-postgres-shared-tenant`

### Review upgrade impact before coding

```text
Review the impact of upgrading the selected stack to <new-stack-id>.
List required spec changes, feature migration risks, configuration changes, deployment/runtime implications, and recommended next steps.
Do not implement yet.
```

### Apply upgrade-related follow-up work

```text
Approved. Apply the stack-upgrade follow-up work for <new-stack-id>.
Update affected feature specs, tasks, configuration contracts, and implementation as required by the new stack baseline.
Then summarize what changed.
```

---

## 11) Role-Based Quick Start

### Product Manager (spec-first flow)

```text
Initialize specs/PRODUCT.md for <product idea>.
Propose the best stack from .ai/STACKS.md with trade-offs, and wait for my choice before implementation.
```

```text
Create feature <NNN-short-title> from specs/features/TEMPLATE/.
Draft clear requirements and acceptance criteria in spec.md and tasks.md.
Do not implement yet.
```

```text
Review specs/features/<feature-id>/spec.md as if for handoff quality.
List ambiguities, missing constraints, and measurable acceptance criteria improvements.
```

### Backend Developer (persistence + API/domain flow)

```text
Implement backend for specs/features/<feature-id>/spec.md.
Focus on service/repository/domain logic, validation, and tests first.
Use selected stack constraints from specs/PRODUCT.md.
```

```text
Add persistence changes for this feature using JPA + Flyway.
Create migrations, update repositories/services, and verify profile behavior for local/test/prod.
```

```text
Run a backend quality pass for this feature:
identify bug risks, transaction boundaries, migration risks, and missing tests.
Then apply fixes.
```

### Full-Stack Developer (end-to-end slice flow)

```text
Implement specs/features/<feature-id>/spec.md end-to-end in one vertical slice:
backend + UI + tests, then summarize acceptance criteria results.
```

```text
For this feature, include runtime readiness checks:
profile config contract, migration validation, and container/deployment implications.
```

```text
Prepare this feature for PR:
final cleanup, test plan checklist, risk notes, and concise PR summary.
```
