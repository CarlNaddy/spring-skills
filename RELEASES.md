# Release And Notification Playbook

Use this playbook for every framework release.

## First-Time Manual Guide (Short)

Use this once to run your first release manually.

1. Ensure your release commit is ready and working tree is clean:

```bash
git status
```

2. Confirm target framework version and tag (example for this release):

- Framework version: `3.4.x.2`
- Git tag: `v3.4.x.2`

3. Create tag locally:

```bash
git tag v3.4.x.2
```

4. Verify tag exists:

```bash
git tag --list "v3.4.x.*"
```

5. Push tag to GitHub:

```bash
git push origin v3.4.x.2
```

6. Create GitHub Release (UI or CLI) using the `CHANGELOG.md` section for that version.

Suggested release metadata:

- Title: `3.4.x.2`
- Supported Spring Boot lane: `3.4.x`
- Lane status: `current`

If a tag is wrong:

```bash
git tag -d v3.4.x.2
git push --delete origin v3.4.x.2
```

## Exact Runbook: Release `3.4.x.2`

Use these exact steps in order.

1. Ensure you are on the intended release commit:

```bash
git status
git log -1 --oneline
```

2. Confirm framework version is already set:

```bash
rg "frameworkVersion" .ai/framework-package.json
```

Expected value:

- `"frameworkVersion": "3.4.x.2"`

3. Create and push tag:

```bash
git tag v3.4.x.2
git push origin v3.4.x.2
```

4. Verify tag is available remotely:

```bash
git ls-remote --tags origin "v3.4.x.2"
```

5. Create release notes file from the `CHANGELOG.md` section (`3.4.x.2 - 2026-04-17`) and save it as:

- `release-notes-3.4.x.2.md`

6. Create GitHub Release:

```bash
gh release create v3.4.x.2 --title "3.4.x.2" --notes-file release-notes-3.4.x.2.md
```

7. Final check on release page:

- Version: `3.4.x.2`
- Supported Spring Boot lane: `3.4.x`
- Lane status: `current`

## Goal

For each version (for example `3.4.x.2`), publish:

- a git tag (`v3.4.x.2`),
- a GitHub Release with "What's new",
- and an external notification (GitHub watchers and optional email).

## Pre-Release Checklist

- [ ] `CHANGELOG.md` has a complete section for the target version.
- [ ] `.ai/framework-package.json` has the target `frameworkVersion`.
- [ ] `VERSIONING.md` still matches the intended lane policy.
- [ ] Current lane compatibility is explicit (for example `3.4.x`).
- [ ] Tests/validation checks are complete.
- [ ] Final release commit is merged into the lane branch.

## Release Steps (Git + GitHub)

1. Create a tag from the release commit:

```bash
git tag v3.4.x.2
git push origin v3.4.x.2
```

2. Create a GitHub Release using the matching `CHANGELOG.md` section:

```bash
gh release create v3.4.x.2 --title "3.4.x.2" --notes-file release-notes-3.4.x.2.md
```

3. Verify the release page includes:

- Version: `3.4.x.2`
- Supported Spring Boot lane: `3.4.x`
- Lane status: `current` / `maintenance` / `eol`
- Highlights and migration notes (if any)

## GitHub Release Notes Template

Copy this for each release:

```md
## Framework Release 3.4.x.2

**Supported Spring Boot lane:** `3.4.x`  
**Lane status:** `current`

### Highlights
- <2-4 high-impact updates>

### What's Changed
- <key change 1>
- <key change 2>
- <key change 3>

### Policy / Compatibility
- Versioning scheme: `<spring-boot-major>.<spring-boot-minor>.x.<framework-release>`
- This release is for Spring Boot `3.4.x` users.

### Migration Notes
- <none or required migration actions>

### Links
- Changelog: `CHANGELOG.md`
- Versioning policy: `VERSIONING.md`
```

## Email Announcement Template (Optional)

Subject:

```text
Spring Skills 3.4.x.2 released (Spring Boot 3.4.x lane)
```

Body:

```text
We released Spring Skills 3.4.x.2.

Supported Spring Boot lane: 3.4.x
Lane status: current

Highlights:
- <highlight 1>
- <highlight 2>
- <highlight 3>

Full release notes:
<GitHub release URL>

Changelog:
<CHANGELOG URL>
```

## Notification Channels

Recommended defaults:

- GitHub Releases (primary channel).
- GitHub "Watch -> Custom -> Releases" for subscriber notifications.
- Optional email list for direct release announcements.

## Multi-Lane Release Notes Guidance

When multiple lanes are active (for example `3.4.x` and `4.0.x`):

- Publish separate tags and releases per lane.
- Keep lane compatibility line in the first lines of release notes.
- Avoid mixing changes from different lanes in one release entry.
- Include lane-specific migration notes only where relevant.
