# Guide: Local UI Assets (No CDN)

Use this guide when the project policy requires Bootstrap/fonts/icons to be served from local files.

## Policy

- Runtime UI dependencies must be local project assets.
- Do not load Bootstrap, fonts, or icon packs from CDN URLs unless spec explicitly allows an exception.

---

## 1) Spring Boot asset locations

Spring Boot serves static resources from classpath locations such as:

- `src/main/resources/static/`
- `src/main/resources/public/`
- `src/main/resources/resources/`
- `src/main/resources/META-INF/resources/`

Recommended project structure:

```text
src/main/resources/static/
  css/
  js/
  vendor/
    bootstrap/
    bootstrap-icons/
  fonts/
```

Keep template references context-relative (for example `/vendor/bootstrap/css/bootstrap.min.css`).

---

## 2) Install Bootstrap locally

Reference: Bootstrap 5 docs (`npm install bootstrap@5.3.x`, compiled `css/` + `js/` artifacts).

### Option A (recommended): npm + copy to static vendor folder

```bash
npm install bootstrap@5.3.8
```

Copy from `node_modules/bootstrap/dist/` into:

- `src/main/resources/static/vendor/bootstrap/css/`
- `src/main/resources/static/vendor/bootstrap/js/`

Use `bootstrap.bundle.min.js` when JS widgets are needed (includes Popper).

### Option B: downloaded compiled files

Download Bootstrap compiled assets and copy only required files into:

- `src/main/resources/static/vendor/bootstrap/css/`
- `src/main/resources/static/vendor/bootstrap/js/`

---

## 3) Install Bootstrap Icons locally

Reference: Bootstrap Icons docs (`npm i bootstrap-icons`).

```bash
npm i bootstrap-icons
```

Copy from package `font/` and/or `bootstrap-icons.svg` into:

- `src/main/resources/static/vendor/bootstrap-icons/`

Then reference local CSS (font mode) or local SVG sprite file.

---

## 4) Fonts and similar assets

- Store webfonts under `src/main/resources/static/fonts/` (or vendor subfolder).
- Reference fonts via local `@font-face` URLs in your CSS.
- If using third-party font packages, vendor the needed files into static assets and reference locally.

---

## 5) Thymeleaf include pattern (local only)

```html
<link rel="stylesheet" th:href="@{/vendor/bootstrap/css/bootstrap.min.css}">
<link rel="stylesheet" th:href="@{/vendor/bootstrap-icons/bootstrap-icons.min.css}">
<script defer th:src="@{/vendor/bootstrap/js/bootstrap.bundle.min.js}"></script>
```

---

## 6) Verification checklist

- No `https://cdn...`, `jsdelivr`, `unpkg`, or `fonts.googleapis.com` references in templates/layouts.
- Browser page source shows local `/css`, `/js`, `/vendor`, `/fonts` URLs.
- Required static files exist under `src/main/resources/static/`.
- App works offline for UI assets.
