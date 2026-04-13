# AI Skill System for Full-Stack Development

*(Inspired by Laravel Boost, Anthropic Skills, and Atomic + Integration Design)*

---

# 1. Objective

The goal of this project is to design a **modular, scalable AI skill system** that enables AI agents (especially in Cursor) to:

* Generate consistent, production-ready code
* Follow predefined architectural patterns
* Avoid common mistakes and hallucinations
* Adapt to different tech stacks dynamically

---

# 2. Problem Statement

Modern AI coding workflows suffer from:

* ❌ Inconsistent architecture decisions
* ❌ Mixing incompatible technologies (e.g., SSR + SPA)
* ❌ Lack of reusable knowledge
* ❌ Poor context management

We needed a system that:

> ✔ Provides structured, reusable knowledge
> ✔ Scales across multiple tech stacks
> ✔ Works naturally with AI editors (Cursor)
> ✔ Remains simple and maintainable

---

# 3. Core Idea

The solution is based on **two key concepts**:

---

## 3.1 🧩 Atomic Skills

> A skill represents **one technology or feature**

Examples:

* `spring-rest-api`
* `react-frontend`
* `auth-jwt`
* `tailwindcss`

### Characteristics:

* Independent
* Reusable
* Single responsibility
* No knowledge of other technologies

---

## 3.2 🔗 Integration Skills

> A skill that defines **how technologies work together**

Examples:

* `spring-react-integration`
* `spring-thymeleaf-htmx-integration`

### Responsibilities:

* Define architecture
* Define communication patterns
* Prevent conflicts
* Enforce best practices

---

## 🔥 Key Insight

> The separation of **technology (atomic skills)** and **architecture (integration skills)** is the foundation of this system.

---

# 4. Skill Format (Hybrid Approach)

Inspired by:

* Laravel Boost
* Anthropic

We use a **Markdown-first format with structured sections**:

---

## Example Skill Structure

```md
# Skill: spring-react-integration

## Type
integration

## Requirements
- spring-rest-api
- react-frontend

## Conflicts
- thymeleaf-templates

## When to Use
...

## Rules
...

## Examples
...
```

---

## Why this works

* ✅ Human-readable (ideal for Cursor)
* ✅ Machine-parsable (structured sections)
* ✅ No complex schema required
* ✅ Easy to extend

---

# 5. Resolver System

The **Resolver** is responsible for selecting the correct skills.

---

## Input

* `SPEC.md` (project definition)
* Available skills

---

## Output

* Final list of applicable skills

---

## Resolution Steps

1. Read project SPEC
2. Map stack → atomic skills
3. Map features → feature skills
4. Add integration skill(s)
5. Resolve dependencies (Requirements)
6. Remove conflicts

---

## Example

### SPEC

```md
Backend: Spring Boot (REST)
Frontend: React
Auth: JWT
```

---

### Result

```plaintext
spring-rest-api
react-frontend
auth-jwt
spring-react-integration
```

---

# 6. Project Structure

```plaintext
ai/
  skills/
    spring-rest-api/
      SKILL.md
    react-frontend/
      SKILL.md
    auth-jwt/
      SKILL.md
    tailwindcss/
      SKILL.md
    spring-react-integration/
      SKILL.md

  SPEC.md
```

---

# 7. How to Use

---

## Step 1: Define Project

Edit `ai/SPEC.md`:

```md
Backend: Spring Boot (REST)
Frontend: React
Styling: TailwindCSS
Auth: JWT
```

---

## Step 2: Open in Cursor

Open the project in Cursor.

---

## Step 3: Prompt the AI

```text
Read ai/SPEC.md and all skills in ai/skills.
Determine relevant skills and follow their rules strictly.
```

---

## Step 4: Generate Code

Ask for:

* project structure
* initial implementation
* features

---

## Expected Behavior

* Correct architecture
* No conflicting technologies
* Consistent patterns

---

# 8. Design Principles

---

## 1. Single Responsibility

Each skill must do exactly one thing.

---

## 2. No Duplication

Logic must not be repeated across skills.

---

## 3. Explicit Constraints

Always define what **NOT to do**.

---

## 4. Strong Defaults

Define:

* API paths
* patterns
* conventions

---

## 5. Example-Driven

Examples improve AI output significantly.

---

# 9. Extending the System (Future Plan)

---

## 🧩 Adding a New Atomic Skill

### Step 1: Create folder

```plaintext
ai/skills/new-skill/
  SKILL.md
```

---

### Step 2: Define structure

```md
# Skill: new-skill

## Type
core | frontend | feature | styling

## When to Use
...

## Rules
...

## Examples
...

## Anti-Patterns
...
```

---

## 🔗 Adding a New Integration Skill

### Step 1: Identify combination

Example:

* Spring + Vue

---

### Step 2: Create integration skill

```md
# Skill: spring-vue-integration

## Type
integration

## Requirements
- spring-rest-api
- vue-frontend

## Conflicts
- react-frontend
```

---

### Step 3: Define architecture

* Communication pattern
* Rendering strategy
* API structure

---

## ⚠️ Important Rule

> Integration skills must NOT contain business logic
> Only architecture and interaction rules

---

# 10. Scaling Strategy

---

## Phase 1 (Current)

* Core stack (Spring + React)
* Basic auth
* Styling

---

## Phase 2

* More frontend frameworks (Vue, Angular)
* Database skills
* Testing skills

---

## Phase 3

* Advanced features:

  * Payments
  * Realtime
  * Microservices

---

## Phase 4

* Automation:

  * Auto skill selection
  * Conflict resolution improvements

---

# 11. Key Benefits

---

## 🚀 Productivity

* Faster development
* Less decision fatigue

---

## 🧠 Consistency

* Same architecture across projects

---

## 🔒 Safety

* Prevents bad patterns

---

## 🧩 Scalability

* Easy to extend

---

# 12. Final Summary

We built a system that:

* Uses **atomic skills** for technologies
* Uses **integration skills** for architecture
* Uses a **resolver** to combine them
* Uses **Markdown-based structured skills**
* Is optimized for AI tools like Cursor

---

## 🔥 Core Insight

> The real power is not in individual skills
> but in **how they are combined and constrained**

---

## 🚀 Next Steps

* Expand skill library
* Improve examples
* Add more integration scenarios
* Test across real projects

---

**This system provides a strong foundation for building a scalable AI-driven development workflow.**
