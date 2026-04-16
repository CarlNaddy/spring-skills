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

Provide session-based authentication for Spring MVC applications using server-side rendering.

---

## When to Use

Use when:
- using Spring MVC (SSR)
- using Thymeleaf templates
- authentication is form-based
- session-based auth is required

---

## Authentication Flow

1. User submits login form
2. Server validates credentials
3. Session is created
4. User stays authenticated via session cookie

---

## Security Configuration

- Use Spring Security
- Enable form login
- Enable CSRF protection
- Keep login and static assets publicly accessible via `permitAll` rules placed before `anyRequest().authenticated()`
- Prefer `permitAll` for static resources instead of `WebSecurityCustomizer` ignore rules so security headers still apply

---

## Login Configuration Example

```java
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

http
  .csrf().and()
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
    .logoutSuccessUrl("/login")
  );
```

---

## Login Controller

```java
@GetMapping("/login")
public String login() {
    return "auth/login";
}
```

---

## Thymeleaf Login Form

```html
<form th:action="@{/login}" method="post">
  <input type="text" name="username" />
  <input type="password" name="password" />
  <button type="submit">Login</button>
</form>
```

---

## Session Handling

* Session managed automatically by Spring Security
* No manual token handling required

---

## CSRF Handling

* CSRF must be enabled
* Include CSRF token in forms

### Example

```html
<input type="hidden"
       th:name="${_csrf.parameterName}"
       th:value="${_csrf.token}" />
```

---

## HTMX Compatibility

* Ensure CSRF token is included in HTMX requests
* Use headers if needed

---

## Defaults

* Define an authenticated landing page as the route configured by `defaultSuccessUrl(...)` in Spring Security
* Root URL design decision:
  * If an explicit home/root page is defined by the application, map `/` to that page.
  * Otherwise, map `/` to redirect to the same authenticated landing page route.
  * Keep this explicit in controller/security configuration; do not implement runtime filesystem/template existence checks.
* Login URL: `/login`
* Logout URL: `/logout`
* Session-based authentication
* CSRF enabled
* Public static assets for anonymous users:
  * include `PathRequest.toStaticResources().atCommonLocations()`
  * include project-specific static paths used by templates (for example `/vendor/**`)
  * keep these matchers before `anyRequest().authenticated()`

---

## Regression Tests

When security rules or static asset paths are changed, include `MockMvc` tests that verify:

* anonymous `GET` to required static assets (for example `/vendor/...`) returns `200`
* protected application routes still require authentication
* browser-style security behavior is asserted with `Accept: text/html` when testing login redirects (to avoid API-style 401 expectations in ambiguous requests)

---

## Anti-Patterns

* Do NOT use JWT in SSR apps
* Do NOT disable CSRF
* Do NOT store auth state in frontend
* Do NOT mix SPA authentication patterns
