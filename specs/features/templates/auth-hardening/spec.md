# Feature spec: auth hardening

## Summary

Improve authentication robustness and session safety for production readiness.

## Scope

### In scope

- Authentication error handling improvements
- Session security checks and logout behavior
- CSRF coverage for sensitive actions

### Out of scope

- New identity providers
- Full RBAC redesign

## Acceptance criteria

- [ ] Security-sensitive routes are protected and verified
- [ ] Login/logout/session behavior is predictable and tested
- [ ] CSRF protection remains enabled for mutating endpoints

