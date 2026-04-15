# Skill: spring-boot-devtools

## Type
core

## Purpose

Ensure every Spring Boot project includes Developer Tools for faster local development feedback.

---

## When to Use

Use in all Spring Boot-based projects unless explicitly disabled by product requirements.

---

## Rules

- Add `org.springframework.boot:spring-boot-devtools` to `pom.xml`.
- For Maven, set `<optional>true</optional>` so devtools is not transitively inherited.
- Keep devtools as development-only behavior by default.
- Do not enable packaging behavior that forces devtools into production artifacts unless explicitly requested.

---

## Maven Example

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <optional>true</optional>
</dependency>
```

---

## Packaging Guidance

- Default Spring Boot repackaging excludes devtools for production archives.
- Only configure plugin flags to include devtools when remote devtools behavior is explicitly required.

---

## Anti-Patterns

- Do NOT add devtools as a required runtime dependency for production.
- Do NOT omit `<optional>true</optional>` in Maven projects.
