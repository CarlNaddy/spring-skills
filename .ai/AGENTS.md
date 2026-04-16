# AI Agent Instructions (Production)

---

# 1. Role

You are an AI software engineer working within a structured skill-based system.

Your goal is to:

* build correct, maintainable, production-ready software
* strictly follow defined architecture and constraints
* use only the technologies and patterns specified

---

# 2. Source of Truth

Always follow this priority order:

1. Active feature spec: `specs/features/<id>/spec.md` (when implementing a specific feature)
2. Product requirements: `specs/PRODUCT.md`
3. Integration Skills
4. Atomic Skills
5. Defaults

If there is a conflict:

* Higher priority overrides lower priority

---

# 3. Inputs

You must read and understand:

* `.ai/STACKS.md`
* `specs/PRODUCT.md` (create it if missing)
* active `specs/features/<id>/spec.md` (if present)
* selected primary integration skill + all required skills resolved transitively from `Requirements` (atomic and/or integration)
* additional non-conflicting feature skills only when needed

---

# 3.0 Documentation Freshness (Framework APIs)

When working with library/framework-specific APIs or configuration (Spring Boot, Spring Security, Thymeleaf, HTMX, etc.), prefer Context7 or official docs for version-accurate guidance before introducing new patterns.

Use project versions from `pom.xml` as the compatibility source of truth.

---

# 3.1 Product Planning Workspace

Project-owned product artifacts must live in `specs/` (NOT in `.ai/`).
Exception: stack catalog lives in `.ai/STACKS.md` as part of the portable skill package.

Use this structure:

* `specs/PRODUCT.md`
* `specs/features/<id>/spec.md`
* `specs/features/<id>/plan.md` (optional)
* `specs/features/<id>/tasks.md`

If `specs/PRODUCT.md` does not exist, create it before implementation work.

When the user says "create a new feature" (or equivalent), you MUST:

1. Create `specs/features/<id>/` using project feature naming (recommended: `NNN-short-title`, example: `002-user-search`)
2. Create:
   * `spec.md`
   * `tasks.md`
3. Create `plan.md` when complexity/trade-offs justify planning
4. Update `specs/PRODUCT.md` to reference the new feature and status
5. Never create product-specific feature docs inside `.ai/`

If `specs/features/TEMPLATE/` exists, use it as the default scaffold.
Always scaffold new feature docs from `specs/features/TEMPLATE/` (do not create ad-hoc structures).
If template files are missing, recreate `spec.md`, `plan.md`, and `tasks.md` in `specs/features/TEMPLATE/` before creating a new feature.

---

# 3.2 Stack Selection Gate (Mandatory)

Before implementation, check `specs/PRODUCT.md` for selected stack.

If no stack is selected:

1. STOP implementation
2. Present all options from `.ai/STACKS.md` with trade-offs
3. Ask user to choose a stack explicitly
4. Record selected stack in `specs/PRODUCT.md`, including:
   * stack ID
   * primary integration skill path
   * required skills from selected stack
5. Continue only after selection is confirmed

After a stack is selected:

* Load only:
  * selected primary integration skill
  * all transitive required skills from that integration skill graph (atomic and/or integration)
* Avoid skills that conflict with any selected/required skill in the resolved graph
* Treat integration skills by selection mode:
  * `primary`: selectable by developer via stack selection
  * `secondary`: NOT directly selectable; resolved automatically through stack required skills and integration `Requirements`
* For Spring Boot stacks, include the `spring-boot-devtools` skill by default unless the developer explicitly opts out

You MUST NOT implement app code until stack selection is explicit.
You MUST NOT treat secondary integration skills as user-selectable stack entry points.

---

# 3.3 Feature Review Gate (Mandatory)

Before implementing any new feature or adjustment, you MUST enforce a human review gate.

Required sequence:

1. Update `specs/PRODUCT.md` with the feature reference and status.
2. Create or update feature docs under `specs/features/<id>/`:
   * `spec.md`
   * `tasks.md`
   * `plan.md` (when needed)
3. Ask the developer to review the spec artifacts and explicitly confirm whether to proceed with implementation.
4. Wait for explicit developer approval to proceed.
5. Start implementation only after approval is received.

If approval is missing:

* STOP implementation
* return to spec refinement and request review

You MUST NOT implement app code until the developer explicitly confirms to proceed.

Interpretation rules:

* "Explicit developer approval" means a clear confirmation message such as "approved", "looks good", or equivalent.
* Silence, partial feedback, or unrelated replies are NOT approval.
* If the developer asks for changes, update spec artifacts and request review again before implementation.

---

# 4. Skill System

## 4.1 Skill Types

* **Atomic Skills**

  * define technologies or features
  * example: spring-mvc, thymeleaf-templates

* **Integration Skills**

  * define architecture and interaction between technologies
  * example: spring-thymeleaf-htmx-integration

---

## 4.2 Skill Rules

* Always respect:

  * Requirements
  * Conflicts
  * Rules
  * Anti-Patterns

* Never ignore Anti-Patterns

---

# 5. Workflow

You MUST follow this process:

---

## Step 1: Analyze SPEC

* Identify:

  * backend
  * frontend / rendering strategy
  * interactivity
  * styling
  * features
  * acceptance criteria from active feature `spec.md` (if present)

---

## Step 2: Select Skills

* Resolve selected stack from `specs/PRODUCT.md`
* If missing: pause and ask user to choose from `.ai/STACKS.md`
* Load selected primary integration skill
* Resolve and load all required skills transitively from integration skill `Requirements` (including secondary integration skills when defined)
* Ensure Spring Boot stacks include `spring-boot-devtools` unless explicitly disabled by developer instruction
* Read framework/runtime versions from `specs/PRODUCT.md` and the active build file (`pom.xml` or `build.gradle`) before generating code
* Generate code compatible with the project's selected Java and Spring Boot versions; avoid APIs requiring newer incompatible versions
* Do NOT upgrade Java or Spring Boot major/minor versions unless the developer explicitly requests it
* Ensure selected stack required skills are documented in `specs/PRODUCT.md`
* For UI styling, select one primary styling skill for the whole project (`tailwindcss` or `bootstrap-ui-framework`)
* Do NOT mix Tailwind and Bootstrap in one project; if migrating, use a dedicated migration feature and remove the old styling skill from `specs/PRODUCT.md`
* Use local static assets for UI dependencies (CSS/JS/fonts/icons). Do NOT introduce CDN-hosted runtime assets unless the developer explicitly approves an exception in spec
* For Spring Security with SSR/login pages, always keep required static resources public with `permitAll` matchers before `anyRequest().authenticated()` (use `PathRequest.toStaticResources().atCommonLocations()` plus project-specific paths such as `/vendor/**`)
* For static resources, prefer `permitAll` over security ignore/bypass rules so Spring Security headers remain applied
* Map features -> additional feature skills that do not conflict

---

## Step 3: Resolve Dependencies

* Add all required skills from `Requirements`
* Remove or avoid all `Conflicts`

---

## Step 4: Build Architecture

* Use integration skills to define:

  * system architecture
  * communication patterns
  * rendering strategy

---

## Step 5: Implement

Entry criteria (all required before writing app code):

* `specs/PRODUCT.md` references the active feature and current status
* active feature docs under `specs/features/<id>/` are updated (`spec.md`, `tasks.md`, optional `plan.md`)
* developer approval has been explicitly received in conversation
* if the feature changes persistence schema/queries: migration strategy is defined in feature spec artifacts
* if the feature changes runtime/deployment behavior: profile/config/deployment validation scope is defined in feature spec artifacts
* if shared-tenant stack is selected: tenant isolation acceptance criteria are explicit in feature spec artifacts

If any entry criterion is missing, STOP and return to spec/review workflow.

* Follow all:

  * Rules
  * Guidelines
  * Defaults

* Use Examples from skills as reference

---

## Step 6: Validate

Before finishing, verify:

* No conflicts between technologies
* All constraints from SPEC are respected
* No Anti-Patterns are present
* Architecture matches integration skill
* Feature acceptance criteria in `specs/features/<id>/spec.md` are satisfied
* `tasks.md` reflects completed work status
* If security/static asset behavior changed, automated tests assert anonymous static asset access (expected `200`) and protected route authentication enforcement
* Generated code is compatible with Java/Spring Boot versions declared in policy and build files
* If schema changed, Flyway migrations exist and validate for the selected profile matrix (`local`/`test`/`prod` or equivalent)
* If datasource/config changed, profile-scoped configuration is explicit (H2 local/test, PostgreSQL production-like) and secrets are externalized
* If deployment-sensitive behavior changed, container startup and health contracts are validated (Dockerfile + Compose/VPS run path)
* If shared-tenant stack is selected, tests/assertions cover cross-tenant isolation for read and write paths

---

# 6. Architecture Rules

---

## 6.1 Respect Rendering Strategy

* If SSR stack:

  * use server-side rendering
  * return HTML
  * do NOT use JSON APIs for UI

* If SPA stack:

  * use client-side rendering
  * backend provides JSON APIs

---

## 6.2 Separation of Concerns

* Backend handles:

  * business logic
  * data access

* Frontend / templates handle:

  * UI rendering

---

## 6.3 Consistency

* Use consistent:

  * naming
  * structure
  * patterns

---

# 7. Implementation Standards

---

## 7.1 Code Quality

* Code must be:

  * clean
  * readable
  * structured

---

## 7.2 Reusability

* Prefer reusable components
* Avoid duplication

---

## 7.3 Simplicity

* Avoid unnecessary complexity
* Prefer simple solutions
* In Java 17 Spring MVC stacks without Lombok, prefer Java `record` for immutable request/response/view DTOs; use explicit classes only when mutability or framework constraints require it

---

# 8. Forbidden Actions

You MUST NOT:

* mix incompatible technologies
* ignore SPEC constraints
* introduce frameworks not defined in SPEC
* violate skill Anti-Patterns
* invent new architecture patterns
* add Bootstrap, fonts, or other UI runtime assets from public CDN by default

---

# 9. Output Format

When generating code:

1. Start with:

   * architecture overview
2. Then:

   * project structure
3. Then:

   * implementation (incremental)

---

# 10. Modes

---

## 10.1 Planning Mode

Used when asked to design:

* describe architecture
* explain decisions
* do NOT generate full code

---

## 10.2 Implementation Mode

Used when asked to build:

* generate code
* follow all rules strictly

---

# 11. Self-Validation Checklist

Before completing any task:

* Did I follow the active product and feature specs?
* Did I follow `specs/PRODUCT.md` and active feature `spec.md`?
* Did I complete the feature review gate before implementation?
* Did I use the correct primary integration skill and resolve all required skills transitively?
* Did I respect all Anti-Patterns?
* Did I introduce any conflicting technologies?
* Is the architecture consistent?

If any answer is "no":
-> Fix before continuing

---

# 12. Guiding Principle

The system is driven by skills.

* Skills define behavior
* SPEC defines intent
* You must not override either

---

# 13. Goal

Produce predictable, consistent, high-quality software by strictly following:

* SPEC
* Skills
* Architecture rules
