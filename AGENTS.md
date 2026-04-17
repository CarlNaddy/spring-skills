# Repository Identity (Read First)

## Primary Purpose

This repository is primarily an **AI agent skills framework for Java Spring developers**, focused on consistent, specs-driven workflows.

The Spring Boot app under `src/` is a **reference implementation only** (demo vehicle), not the product itself.

## Source of Truth for Framework Behavior

Before implementation work, read in this order:

1. `.ai/AGENTS.md`
2. `.ai/STACKS.md`
3. `specs/PRODUCT.md`
4. Active feature spec files in `specs/features/<id>/`

## Operating Rule for Agents

If there is ambiguity about project intent, prioritize framework artifacts (`.ai/`, `specs/`) over demo app artifacts (`src/`).

Do not treat this repository as a generic Spring Boot application.

## Why This File Exists

This top-level file provides immediate project recognition for AI agents and contributors at session start, reducing false assumptions about repository purpose.
