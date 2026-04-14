#!/usr/bin/env bash
set -euo pipefail

echo "Validating specs structure..."

required_files=(
  "ai/AGENT.md"
  "ai/STACKS.md"
  "specs/README.md"
  "specs/PRODUCT.md"
  "specs/features/README.md"
  "specs/features/TEMPLATE/spec.md"
  "specs/features/TEMPLATE/tasks.md"
)

for file in "${required_files[@]}"; do
  if [[ ! -f "$file" ]]; then
    echo "Missing required file: $file"
    exit 1
  fi
done

required_template_dirs=(
  "specs/features/templates/crud"
  "specs/features/templates/auth-hardening"
  "specs/features/templates/pagination"
)

for dir in "${required_template_dirs[@]}"; do
  if [[ ! -d "$dir" ]]; then
    echo "Missing required template directory: $dir"
    exit 1
  fi
done

for dir in "${required_template_dirs[@]}"; do
  for file in spec.md plan.md tasks.md; do
    if [[ ! -f "$dir/$file" ]]; then
      echo "Missing template file: $dir/$file"
      exit 1
    fi
  done
done

if ! grep -Eq 'Stack ID:[[:space:]]*`[^`]+`' specs/PRODUCT.md; then
  echo "specs/PRODUCT.md must define a selected Stack ID."
  exit 1
fi

if ! grep -Eq 'Integration skill path:[[:space:]]*`[^`]+`' specs/PRODUCT.md; then
  echo "specs/PRODUCT.md must define an integration skill path."
  exit 1
fi

if ! grep -Eq "Required stack skills:" specs/PRODUCT.md; then
  echo "specs/PRODUCT.md must include required stack skills."
  exit 1
fi

echo "Checking feature folders for required files..."

for feature_dir in specs/features/*; do
  [[ -d "$feature_dir" ]] || continue

  name="$(basename "$feature_dir")"
  if [[ "$name" == "TEMPLATE" || "$name" == "templates" ]]; then
    continue
  fi

  if [[ ! -f "$feature_dir/spec.md" ]]; then
    echo "Feature folder missing spec.md: $feature_dir"
    exit 1
  fi

  if [[ ! -f "$feature_dir/tasks.md" ]]; then
    echo "Feature folder missing tasks.md: $feature_dir"
    exit 1
  fi
done

echo "Specs validation passed."

