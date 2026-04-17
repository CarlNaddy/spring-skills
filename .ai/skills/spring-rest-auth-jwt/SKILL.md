# Skill: spring-rest-auth-jwt

## Type
feature

## Requirements

- spring-rest-api

## Conflicts

- spring-mvc-auth-session

---

## Purpose

Provide production-grade stateless authentication and authorization for Spring REST APIs using signed JWT bearer tokens and Spring Security Resource Server.

---

## When to Use

Use when:
- API clients authenticate with `Authorization: Bearer <token>`
- server must remain stateless (`SessionCreationPolicy.STATELESS`)
- service acts as an OAuth2 resource server (token consumer), not a login form app

Do not use for SSR/session-first applications.

---

## Security Baseline (Mandatory)

- Configure `SecurityFilterChain` with:
  - explicit public endpoints first (`/actuator/health`, auth bootstrap endpoints, API docs when required)
  - `.anyRequest().authenticated()` last
  - `.oauth2ResourceServer(oauth2 -> oauth2.jwt(...))`
- Set stateless sessions:
  - `session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)`
- Use bearer tokens in headers only. Do not use query params for tokens.
- Keep HTTPS mandatory in all non-local environments.

---

## Entry Points

- Configure `SecurityFilterChain` as the primary security entry point.
- Configure `JwtDecoder` bean for token validation.
- Configure `application.yml` for `issuer-uri` or `jwk-set-uri`.

---

## Configuration File Policy

- Use `application.yml` as the canonical Spring Boot config format.
- Use profile-specific `application-<profile>.yml` files when needed.
- Do not introduce `application.properties` for new configuration.
- Do not keep both `.properties` and `.yml` in the same module because precedence can hide misconfiguration.

---

## JWT Validation Rules (Mandatory)

- Validate signature using trusted keys (`issuer-uri` or `jwk-set-uri`).
- Validate issuer (`iss`) with `JwtValidators.createDefaultWithIssuer(...)`.
- Validate timestamps (`exp`, `nbf`) and preserve clock skew tolerance (default 60s unless policy requires otherwise).
- Add audience (`aud`) validation for API-specific tokens.
- Reject unsigned tokens and weak/unsafe algorithms.

Use a custom `JwtDecoder` with `DelegatingOAuth2TokenValidator` when audience or custom claim policy is required.

```java
@Bean
JwtDecoder jwtDecoder(@Value("${security.jwt.issuer-uri}") String issuer) {
    NimbusJwtDecoder decoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuer);

    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
    OAuth2TokenValidator<Jwt> audience = new JwtClaimValidator<List<String>>(
        "aud", aud -> aud != null && aud.contains("api://spring-skills")
    );
    OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withIssuer, audience);

    decoder.setJwtValidator(validator);
    return decoder;
}
```

---

## Claims and Authorities Mapping

- Default scope mapping (`scope` / `scp`) to `SCOPE_*` authorities is acceptable for simple APIs.
- For role-based checks, configure a `JwtAuthenticationConverter` explicitly.
- Keep one canonical claim for roles/scopes (avoid mixed claim contracts across issuers).
- Enforce least privilege at endpoint and method layers (`@PreAuthorize` where appropriate).

---

## Token and Key Management

- Prefer asymmetric algorithms (`RS256`/`ES256`) for distributed systems.
- Prefer `issuer-uri` when OIDC discovery is available.
- Use `jwk-set-uri` when discovery is unavailable or startup dependency on issuer metadata must be reduced.
- Allow key rotation through JWK retrieval; do not hardcode static public keys unless required by infrastructure constraints.
- Never log raw JWTs, secrets, or full claims payloads in production logs.

---

## CSRF and CORS Guidance

- For pure bearer-token APIs that do not use cookie/session authentication, disabling CSRF is acceptable.
- If browser cookie auth or cookie-based token transport is introduced, re-evaluate and enable CSRF protections.
- Configure CORS explicitly (allowed origins, methods, headers, credentials policy) for browser clients.

---

## Endpoint Policy Defaults

- Public (explicit allowlist only):
  - health and readiness endpoints required by operations
  - token issuance/refresh endpoints only when this service owns auth flows
- Protected:
  - all business endpoints by default
- Deny by default:
  - everything not explicitly allowed

---

## Testing and Verification (Required)

When security config changes, add/maintain tests that verify:
- missing/invalid/expired token -> `401`
- valid token without required scope/role -> `403`
- valid token with required scope/role -> success (`200/2xx`)
- public endpoints remain reachable without auth
- protected endpoints never become anonymously accessible

Prefer integration tests with `spring-security-test` JWT support and explicit authority assertions.

---

## Output (Required)

The implementation MUST include:

- `SecurityConfig` class with `SecurityFilterChain` bean
- `application.yml` properties for JWT configuration
- `JwtDecoder` bean (custom when audience validation is required)
- at least one protected controller endpoint
- Optional: `JwtAuthenticationConverter` when roles are used

---

## Anti-Patterns

- Do NOT mix session-based auth with JWT resource-server flows in the same API surface without explicit architecture.
- Do NOT trust JWT claims without signature and issuer validation.
- Do NOT disable validation checks (`iss`, `exp`, `nbf`, `aud`) for convenience.
- Do NOT store access tokens in local storage for browser apps handling sensitive data.
- Do NOT put tokens in URLs or application logs.
- Do NOT rely on defaults when custom claim mapping is required by your authorization model.

---

## References (Context7-aligned)

- Spring Security Resource Server JWT (servlet): https://docs.spring.io/spring-security/reference/6.5/servlet/oauth2/resource-server/jwt.html
- Spring Security CSRF guidance (servlet): https://docs.spring.io/spring-security/reference/6.5/servlet/exploits/csrf.html
- Spring Boot OAuth2 Resource Server JWT properties: https://docs.spring.io/spring-boot/docs/3.4.1/reference/htmlsingle/#web.security