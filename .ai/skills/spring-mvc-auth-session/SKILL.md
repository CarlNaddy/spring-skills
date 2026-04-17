# Skill: spring-mvc-auth-session

## Type
feature

## Requirements

- spring-mvc
- thymeleaf-templates

## Conflicts

- spring-rest-auth-jwt
- react-frontend

---

## Purpose

Provide production-grade, session-based authentication for server-rendered Spring MVC applications using form login and Thymeleaf.

---

## When to Use

Use when:
- Building an SSR application with Spring MVC + Thymeleaf.
- Authentication is form-based and browser-driven.
- Server-managed session cookies are the authentication mechanism.

Do not use for stateless REST APIs that require bearer tokens.

---

## Authentication Flow

1. User submits credentials to the login endpoint.
2. Spring Security authenticates the user.
3. Server establishes an authenticated session.
4. Browser sends the session cookie on subsequent requests.

---

## Entry Points

- Configure `SecurityFilterChain` for request authorization and form login.
- Configure login/logout pages and controller mappings.
- Configure `application.yml` for security properties.
- Configure templates/forms to include CSRF tokens.

---

## Security Baseline (Mandatory)

- Use Spring Security form login (`formLogin(...)`) for browser authentication.
- Keep CSRF protection enabled for server-rendered forms.
- Define `permitAll` rules for login, error, and required static resources before `.anyRequest().authenticated()`.
- Prefer `permitAll` matchers for static resources over bypass/ignore rules so security headers remain applied.
- Keep session fixation protection enabled (Spring Security defaults are secure; customize only with explicit requirements).
- Keep logout as a state-changing protected flow (POST with CSRF token by default).

---

## Login Configuration Example

```java
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/error").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/vendor/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
            );
        return http.build();
    }
}
```

---

## CSRF and HTMX Guidance

- Include CSRF tokens in all state-changing form submissions.
- For HTMX requests that mutate state, send CSRF as request header or parameter.
- Do not disable CSRF in SSR apps for convenience.

Thymeleaf hidden input pattern:

```html
<input type="hidden"
       th:name="${_csrf.parameterName}"
       th:value="${_csrf.token}" />
```

---

## Defaults

- Login page: `/login`
- Logout endpoint: `/logout`
- Session-based authentication with server-managed cookie
- CSRF enabled
- Static resources exposed via explicit allowlist matchers
- Authenticated landing page defined via `defaultSuccessUrl(...)`

Root URL policy:
- If a dedicated home page exists, map `/` to it.
- Otherwise, map `/` to redirect to the authenticated landing page.
- Keep this explicit in controller/security configuration.

---

## Configuration File Policy

- Use `application.yml` as the canonical Spring Boot configuration file.
- Use `application-<profile>.yml` for profile-specific settings.
- Do not add new `application.properties` files.
- Do not mix `.properties` and `.yml` in the same module.

---

## Testing and Verification (Required)

When security rules or templates change, include tests that verify:
- Anonymous access to required static assets returns `200`.
- Anonymous requests to protected pages are redirected to login.
- Authenticated users can access protected pages.
- CSRF is enforced on state-changing requests.
- Login and logout flows behave as expected for browser requests.

Prefer `@WebMvcTest` + `MockMvc` for controller/security contract tests.
Use `@WithMockUser` where appropriate for authorization behavior.

---

## Output (Required)

The implementation MUST include:

- `SecurityConfig` class with a `SecurityFilterChain` bean.
- Login page endpoint/controller mapping.
- At least one protected MVC route.
- Thymeleaf forms that include CSRF token fields for state-changing actions.
- Regression tests for auth redirects, protected route access, and CSRF enforcement.
- `application.yml` entries for security-related settings.

---

## Anti-Patterns

- Do NOT use JWT/bearer-token patterns for SSR session-auth flows.
- Do NOT disable CSRF in a form-based MVC application.
- Do NOT expose protected pages via broad `permitAll` matchers.
- Do NOT place business authorization logic only in templates.
- Do NOT mix SPA and SSR authentication patterns without explicit architecture boundaries.

---

## References (Context7-aligned)

- Spring Security 6.5 Form Login (servlet): https://docs.spring.io/spring-security/reference/6.5/servlet/authentication/passwords/form.html
- Spring Security 6.5 CSRF (servlet): https://docs.spring.io/spring-security/reference/6.5/servlet/exploits/csrf.html
- Spring Security 6.5 Java Configuration: https://docs.spring.io/spring-security/reference/6.5/servlet/configuration/java.html
- Spring Boot 3.4 Testing (WebMvcTest, MockMvc): https://docs.spring.io/spring-boot/3.4/reference/testing/spring-boot-applications.html
