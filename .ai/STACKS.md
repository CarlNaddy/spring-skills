# Available stacks

This is the stack catalog provided by the AI skill package.

When no stack is selected in `specs/PRODUCT.md`, choose one of the stacks below before implementation.

After a stack is chosen, save the decision in `specs/PRODUCT.md` under `Selected stack`, including required skills.

---

## Stack: `spring-thymeleaf-htmx`

- Integration skill: `.ai/skills/spring-thymeleaf-htmx-integration/SKILL.md`
- Rendering model: SSR (server-rendered HTML)
- UI communication: server returns HTML fragments/pages (not JSON)
- Baseline runtime target:
  - Java `17`
  - Spring Boot `3.4.x`
- Best for:
  - server-rendered apps
  - progressive enhancement with HTMX
  - teams that prefer backend-driven UI
- Trade-offs:
  - less client-side UI flexibility than SPA frameworks
  - template composition discipline required

### Required skills

- `spring-mvc`
- `thymeleaf-templates`
- `htmx-interactions`
- `spring-boot-devtools`

### Common optional UI skills

- `tailwindcss`
- `bootstrap-ui-framework`

Use exactly one primary CSS framework skill per project.
Do not combine `tailwindcss` and `bootstrap-ui-framework` in the same project baseline.

### Conflicts

- `react-frontend`
- `spring-rest-api`

---

## Stack: `spring-react`

- Integration skill: `.ai/skills/spring-react-integration/SKILL.md`
- Rendering model: SPA (client-rendered)
- UI communication: REST + JSON
- Baseline runtime target:
  - Java `17`
  - Spring Boot `3.4.x`
- Best for:
  - rich client interactivity
  - highly dynamic frontend flows
  - teams with strong frontend specialization
- Trade-offs:
  - more moving parts (frontend + backend separation)
  - requires API contract management

### Required skills

- `spring-rest-api`
- `react-frontend`
- `spring-boot-devtools`

### Conflicts

- `thymeleaf-templates`

---

## Stack: `spring-thymeleaf-htmx-postgres`

- Integration skill: `.ai/skills/spring-thymeleaf-htmx-integration/SKILL.md`
- Persistence integration: `.ai/skills/spring-jpa-flyway-postgres-integration/SKILL.md`
- Local/runtime integration: `.ai/skills/spring-local-runtime-integration/SKILL.md`
- Container/runtime integration: `.ai/skills/spring-container-runtime-integration/SKILL.md`
- Rendering model: SSR (server-rendered HTML)
- UI communication: server returns HTML fragments/pages (not JSON)
- Baseline runtime target:
  - Java `17`
  - Spring Boot `3.4.x`
- Best for:
  - server-rendered line-of-business apps with durable persistence
  - admin panels and operations tools requiring migration discipline
  - teams needing local-to-deployment container parity
- Trade-offs:
  - more setup and policy overhead than in-memory-only prototypes
  - requires migration and profile hygiene across environments

### Required skills

- `spring-mvc`
- `thymeleaf-templates`
- `htmx-interactions`
- `spring-data-jpa`
- `flyway-migrations`
- `postgresql-config`
- `h2-dev-config`
- `docker-compose-local`
- `dockerfile-spring-boot`
- `docker-vps-deployment`
- `spring-jpa-flyway-postgres-integration`
- `spring-local-runtime-integration`
- `spring-container-runtime-integration`
- `spring-boot-devtools`

### Common optional UI skills

- `tailwindcss`
- `bootstrap-ui-framework`

Use exactly one primary CSS framework skill per project.
Do not combine `tailwindcss` and `bootstrap-ui-framework` in the same project baseline.

### Conflicts

- `react-frontend`
- `spring-rest-api`

---

## Stack: `spring-react-postgres`

- Integration skill: `.ai/skills/spring-react-integration/SKILL.md`
- Persistence integration: `.ai/skills/spring-jpa-flyway-postgres-integration/SKILL.md`
- Local/runtime integration: `.ai/skills/spring-local-runtime-integration/SKILL.md`
- Container/runtime integration: `.ai/skills/spring-container-runtime-integration/SKILL.md`
- Rendering model: SPA (client-rendered)
- UI communication: REST + JSON
- Baseline runtime target:
  - Java `17`
  - Spring Boot `3.4.x`
- Best for:
  - SaaS frontends with rich client interaction and durable persistence
  - API-first products with strong separation of concerns
- Trade-offs:
  - highest operational complexity among default stacks
  - requires API lifecycle and migration lifecycle discipline in parallel

### Required skills

- `spring-rest-api`
- `react-frontend`
- `spring-data-jpa`
- `flyway-migrations`
- `postgresql-config`
- `h2-dev-config`
- `docker-compose-local`
- `dockerfile-spring-boot`
- `docker-vps-deployment`
- `spring-jpa-flyway-postgres-integration`
- `spring-local-runtime-integration`
- `spring-container-runtime-integration`
- `spring-boot-devtools`

### Conflicts

- `thymeleaf-templates`

---

## Stack: `spring-thymeleaf-htmx-postgres-shared-tenant`

- Integration skill: `.ai/skills/spring-thymeleaf-htmx-integration/SKILL.md`
- Persistence integration: `.ai/skills/spring-jpa-flyway-postgres-integration/SKILL.md`
- Local/runtime integration: `.ai/skills/spring-local-runtime-integration/SKILL.md`
- Container/runtime integration: `.ai/skills/spring-container-runtime-integration/SKILL.md`
- Tenant integration: `.ai/skills/spring-shared-tenant-integration/SKILL.md`
- Rendering model: SSR (server-rendered HTML)
- UI communication: server returns HTML fragments/pages (not JSON)
- Baseline runtime target:
  - Java `17`
  - Spring Boot `3.4.x`
- Best for:
  - multi-tenant admin and back-office products with SSR workflows
  - teams requiring strict tenant isolation without full tenant database split
- Trade-offs:
  - tenant-context enforcement must be designed and tested across all paths
  - migration design requires tenant-safe indexing and constraints

### Required skills

- `spring-mvc`
- `thymeleaf-templates`
- `htmx-interactions`
- `spring-data-jpa`
- `flyway-migrations`
- `postgresql-config`
- `h2-dev-config`
- `docker-compose-local`
- `dockerfile-spring-boot`
- `docker-vps-deployment`
- `shared-db-multitenancy`
- `spring-jpa-flyway-postgres-integration`
- `spring-local-runtime-integration`
- `spring-container-runtime-integration`
- `spring-shared-tenant-integration`
- `spring-boot-devtools`

### Common optional UI skills

- `tailwindcss`
- `bootstrap-ui-framework`

Use exactly one primary CSS framework skill per project.
Do not combine `tailwindcss` and `bootstrap-ui-framework` in the same project baseline.

### Conflicts

- `react-frontend`
- `spring-rest-api`

---

## Stack: `spring-react-postgres-shared-tenant`

- Integration skill: `.ai/skills/spring-react-integration/SKILL.md`
- Persistence integration: `.ai/skills/spring-jpa-flyway-postgres-integration/SKILL.md`
- Local/runtime integration: `.ai/skills/spring-local-runtime-integration/SKILL.md`
- Container/runtime integration: `.ai/skills/spring-container-runtime-integration/SKILL.md`
- Tenant integration: `.ai/skills/spring-shared-tenant-integration/SKILL.md`
- Rendering model: SPA (client-rendered)
- UI communication: REST + JSON
- Baseline runtime target:
  - Java `17`
  - Spring Boot `3.4.x`
- Best for:
  - multi-tenant SaaS with rich frontend requirements
  - API-first products with strict tenant isolation requirements
- Trade-offs:
  - highest application and operational complexity
  - requires strong tenant-aware authorization and testing discipline

### Required skills

- `spring-rest-api`
- `react-frontend`
- `spring-data-jpa`
- `flyway-migrations`
- `postgresql-config`
- `h2-dev-config`
- `docker-compose-local`
- `dockerfile-spring-boot`
- `docker-vps-deployment`
- `shared-db-multitenancy`
- `spring-jpa-flyway-postgres-integration`
- `spring-local-runtime-integration`
- `spring-container-runtime-integration`
- `spring-shared-tenant-integration`
- `spring-boot-devtools`

### Conflicts

- `thymeleaf-templates`

