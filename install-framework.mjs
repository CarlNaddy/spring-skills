#!/usr/bin/env node

import { createHash } from "node:crypto";
import { mkdir, readFile, stat, writeFile } from "node:fs/promises";
import path from "node:path";
import { fileURLToPath } from "node:url";

const MANIFEST_FILE = ".spring-skills.json";
const REMOTE_PACKAGE_URL =
  "https://raw.githubusercontent.com/CarlNaddy/spring-skills/main/framework-package.json";
const SCRIPT_DIR = path.dirname(fileURLToPath(import.meta.url));

function sha256(text) {
  return createHash("sha256").update(text, "utf8").digest("hex");
}

async function exists(targetPath) {
  try {
    await stat(targetPath);
    return true;
  } catch {
    return false;
  }
}

async function fetchText(url) {
  const response = await fetch(url, {
    headers: {
      "user-agent": "spring-skills-installer"
    }
  });

  if (!response.ok) {
    throw new Error(`Failed to fetch ${url}: ${response.status} ${response.statusText}`);
  }

  return await response.text();
}

async function fetchJson(url) {
  return JSON.parse(await fetchText(url));
}

async function readTextIfExists(targetPath) {
  if (!(await exists(targetPath))) {
    return null;
  }
  return await readFile(targetPath, "utf8");
}

function productSpecTemplate(version) {
  return `# Product requirements

## Summary

Describe the product goal here.

## Selected stack

- Stack ID: <choose from .ai/STACKS.md>
- Primary integration skill: <filled after stack selection>
- Required skills:
  - <filled after stack selection>

## Runtime baseline

- Java: 17
- Spring Boot: 3.4.x
- Version policy: do not upgrade Java or Spring Boot major/minor versions without explicit approval.

## UI baseline

- Primary UI skill: <optional: tailwindcss or bootstrap-ui-framework>

## Framework metadata

- Spring Skills framework version: ${version}
`;
}

function rawFileUrl(repo, branch, relativePath) {
  const encodedPath = relativePath.split("/").map(encodeURIComponent).join("/");
  return `https://raw.githubusercontent.com/${repo}/${branch}/${encodedPath}`;
}

async function ensureParentDir(targetPath) {
  await mkdir(path.dirname(targetPath), { recursive: true });
}

async function loadLocalState(rootDir) {
  const manifestPath = path.join(rootDir, MANIFEST_FILE);
  if (!(await exists(manifestPath))) {
    return null;
  }

  try {
    return JSON.parse(await readFile(manifestPath, "utf8"));
  } catch (error) {
    throw new Error(`Failed to parse ${MANIFEST_FILE}: ${error.message}`);
  }
}

async function writeLocalState(rootDir, state) {
  const manifestPath = path.join(rootDir, MANIFEST_FILE);
  await writeFile(manifestPath, `${JSON.stringify(state, null, 2)}\n`, "utf8");
}

async function installOrUpdate() {
  const rootDir = process.cwd();
  const localPackagePath = path.join(SCRIPT_DIR, "framework-package.json");
  const useLocalPackage = await exists(localPackagePath);
  const packageInfo = useLocalPackage
    ? JSON.parse(await readFile(localPackagePath, "utf8"))
    : await fetchJson(REMOTE_PACKAGE_URL);
  const localState = await loadLocalState(rootDir);
  const managedState = localState?.managedFiles ?? {};

  const summary = {
    installed: [],
    updated: [],
    unchanged: [],
    skippedModified: [],
    skippedExisting: [],
    created: []
  };

  const nextState = {
    frameworkVersion: packageInfo.frameworkVersion,
    sourceRepo: packageInfo.sourceRepo,
    sourceBranch: packageInfo.sourceBranch,
    installedAt: new Date().toISOString(),
    managedFiles: {}
  };

  for (const relativePath of packageInfo.managedFiles) {
    const remoteContent = useLocalPackage
      ? await readFile(path.join(SCRIPT_DIR, relativePath), "utf8")
      : await fetchText(rawFileUrl(packageInfo.sourceRepo, packageInfo.sourceBranch, relativePath));
    const remoteChecksum = sha256(remoteContent);
    const targetPath = path.join(rootDir, relativePath);
    const localContent = await readTextIfExists(targetPath);
    const previousEntry = managedState[relativePath];

    if (localContent === null) {
      await ensureParentDir(targetPath);
      await writeFile(targetPath, remoteContent, "utf8");
      summary[localState ? "updated" : "installed"].push(relativePath);
      nextState.managedFiles[relativePath] = { checksum: remoteChecksum };
      continue;
    }

    const localChecksum = sha256(localContent);

    if (!localState) {
      summary.skippedExisting.push(relativePath);
      continue;
    }

    if (!previousEntry) {
      summary.skippedExisting.push(relativePath);
      continue;
    }

    if (localChecksum !== previousEntry.checksum) {
      summary.skippedModified.push(relativePath);
      nextState.managedFiles[relativePath] = previousEntry;
      continue;
    }

    if (localChecksum === remoteChecksum) {
      summary.unchanged.push(relativePath);
      nextState.managedFiles[relativePath] = { checksum: remoteChecksum };
      continue;
    }

    await writeFile(targetPath, remoteContent, "utf8");
    summary.updated.push(relativePath);
    nextState.managedFiles[relativePath] = { checksum: remoteChecksum };
  }

  const productPath = path.join(rootDir, "specs", "PRODUCT.md");
  if (!(await exists(productPath))) {
    await ensureParentDir(productPath);
    await writeFile(productPath, productSpecTemplate(packageInfo.frameworkVersion), "utf8");
    summary.created.push("specs/PRODUCT.md");
  }

  await writeLocalState(rootDir, nextState);

  const action = localState ? "updated" : "installed";
  console.log(
    `Spring Skills framework ${action}: ${packageInfo.frameworkVersion} from ${packageInfo.sourceRepo}`
  );

  for (const key of ["installed", "updated", "created", "unchanged", "skippedModified", "skippedExisting"]) {
    if (summary[key].length === 0) {
      continue;
    }
    console.log(`\n${labelFor(key)}:`);
    for (const filePath of summary[key]) {
      console.log(`- ${filePath}`);
    }
  }

  console.log("\nNext steps:");
  console.log("1. Open specs/PRODUCT.md and choose a stack from .ai/STACKS.md.");
  console.log("2. Optionally set a primary UI skill: tailwindcss or bootstrap-ui-framework.");
  console.log("3. Use docs/PROMPT-PLAYBOOK.md for copy-paste prompts.");
}

function labelFor(key) {
  switch (key) {
    case "installed":
      return "Installed";
    case "updated":
      return "Updated";
    case "created":
      return "Created";
    case "unchanged":
      return "Already current";
    case "skippedModified":
      return "Skipped because of local changes";
    case "skippedExisting":
      return "Skipped because file already existed before framework management";
    default:
      return key;
  }
}

installOrUpdate().catch((error) => {
  console.error(`Spring Skills installer failed: ${error.message}`);
  process.exitCode = 1;
});
