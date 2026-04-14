# Available stacks

This is the stack catalog provided by the AI skill package.

When no stack is selected in `specs/PRODUCT.md`, choose one of the stacks below before implementation.

After a stack is chosen, save the decision in `specs/PRODUCT.md` under `Selected stack`.

---

## Stack: `spring-thymeleaf-htmx`

- Integration skill: `ai/skills/spring-thymeleaf-htmx-integration/SKILL.md`
- Rendering model: SSR (server-rendered HTML)
- UI communication: server returns HTML fragments/pages (not JSON)
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

### Conflicts

- `react-frontend`
- `spring-rest-api`

---

## Stack: `spring-react`

- Integration skill: `ai/skills/spring-react-integration/SKILL.md`
- Rendering model: SPA (client-rendered)
- UI communication: REST + JSON
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

### Conflicts

- `thymeleaf-templates`

