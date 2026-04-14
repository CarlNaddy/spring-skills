# Spring Boot Full-Stack Skills Framework

A reusable AI framework for building Java web apps with consistent architecture and fast feature delivery.

This repository showcases:
- a portable skills system (`ai/`),
- a specs-driven feature workflow (`specs/`),
- and a reference application used only as an example implementation.

## Skills First

The core value is the AI skills framework:

- `ai/AGENT.md` defines workflow and guardrails.
- `ai/skills/**` contains atomic + integration skills.
- `ai/STACKS.md` is the stack catalog the agent uses for selection.
- The agent must record selected stack and required skills in `specs/PRODUCT.md` before implementation.

## Supported Java/Spring Stacks

Defined in `ai/STACKS.md`:

- `spring-thymeleaf-htmx`
  - Spring MVC (SSR), Thymeleaf templates, HTMX interactivity
  - HTML-first, no JSON UI APIs
- `spring-react`
  - Spring REST API + React SPA
  - JSON-first client/server separation

## Example Implementation

The included Spring app is a demo vehicle that shows the framework in action:

- Spring MVC + Thymeleaf + HTMX
- Session authentication
- User management flows
- Specs-first feature delivery

## Prompt A New Feature Fast

Use prompts like:

- "Let's create feature `002-user-search`"
- "Create a new feature for exporting users"

Expected flow:

1. Agent checks selected stack (or asks to choose from `ai/STACKS.md`).
2. Agent scaffolds `specs/features/<id>/`.
3. Agent creates:
   - `spec.md` (requirements + acceptance criteria)
   - `tasks.md` (execution checklist)
   - `plan.md` (optional, when design trade-offs exist)
4. Implementation follows the selected skill set.

## Specs-Driven Workflow

1. Set product direction in `specs/PRODUCT.md`.
2. Create a feature folder: `specs/features/<id>/`.
3. Use template files from `specs/features/TEMPLATE/`.
4. Implement and verify against acceptance criteria.

## Check It Out Fast

1. Run the app:
   - `mvn spring-boot:run`
2. Open:
   - `http://localhost:8080/login`
3. Sign in:
   - username: `admin`
   - password: `password`

## Repository Map

- `ai/` - portable AI framework (`AGENT.md`, skills, stack catalog)
- `specs/` - product and feature requirements
- `src/` - Spring Boot application code

---

If you want to review the process design, start with:

- `docs/product/REPOSITORY-LAYOUT.md`
- `docs/product/PRODUCT-DEVELOPMENT-PLAN.md`
