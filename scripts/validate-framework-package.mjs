#!/usr/bin/env node

import { readFile, stat } from "node:fs/promises";
import path from "node:path";
import { fileURLToPath } from "node:url";

const SCRIPT_DIR = path.dirname(fileURLToPath(import.meta.url));
const ROOT_DIR = path.resolve(SCRIPT_DIR, "..");
const PACKAGE_PATH = path.join(ROOT_DIR, ".ai", "framework-package.json");

async function exists(targetPath) {
  try {
    await stat(targetPath);
    return true;
  } catch {
    return false;
  }
}

async function main() {
  const packageInfo = JSON.parse(await readFile(PACKAGE_PATH, "utf8"));
  const missingFiles = [];

  for (const relativePath of packageInfo.managedFiles ?? []) {
    const absolutePath = path.join(ROOT_DIR, relativePath);
    if (!(await exists(absolutePath))) {
      missingFiles.push(relativePath);
    }
  }

  if (missingFiles.length > 0) {
    console.error(".ai/framework-package.json references missing managed files:");
    for (const filePath of missingFiles) {
      console.error(`- ${filePath}`);
    }
    process.exitCode = 1;
    return;
  }

  console.log(
    `.ai/framework-package.json is valid: ${packageInfo.managedFiles.length} managed files present.`
  );
}

main().catch((error) => {
  console.error(`Framework package validation failed: ${error.message}`);
  process.exitCode = 1;
});
