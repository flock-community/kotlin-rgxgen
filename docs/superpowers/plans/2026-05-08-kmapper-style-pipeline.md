# Pipeline like kmapper, adapted for kotlin-rgxgen — Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace the minimal existing CI workflow with a kmapper-shaped two-workflow pipeline (`build.yml` for PR validation, `deploy.yml` for releases), and migrate `build.gradle.kts` publishing to the `nexus-publish-plugin` with Central Portal URLs and in-memory GPG signing so the deploy workflow's `publishToSonatype closeAndReleaseSonatypeStagingRepository` command resolves.

**Architecture:** Three coordinated changes to `build.gradle.kts` (add plugin, replace inner `repositories { maven { ... } }` block with top-level `nexusPublishing { sonatype { ... } }`, switch `useGpgCmd()` → `useInMemoryPgpKeys(...)`), then rewrite of `.github/workflows/build.yml` and creation of `.github/workflows/deploy.yml`. Both workflows run on `macos-latest` so every declared KMP target compiles. JDK 21 temurin via `actions/setup-java@v4`.

**Tech Stack:** Kotlin Multiplatform 2.0.21, Gradle (Kotlin DSL), `io.github.gradle-nexus.publish-plugin` 2.0.0, GitHub Actions (`actions/checkout@v4`, `actions/setup-java@v4`, `gradle/actions/setup-gradle@v3`).

**Spec:** [`docs/superpowers/specs/2026-05-08-kmapper-style-pipeline-design.md`](../specs/2026-05-08-kmapper-style-pipeline-design.md)

---

## Pre-flight verification

Before starting, confirm the engineer is on the right branch and the working tree is clean.

- [ ] **Step 0a: Confirm branch and clean tree**

```bash
cd /Users/wilmveel/Projects/kotlin-rgxgen
git rev-parse --abbrev-ref HEAD
git status --porcelain
```

Expected: branch is `copilot/add-wasmjs-wasmwasi-linuxarm64-ios-targets` (or whatever working branch was chosen — do not require this exact name; just confirm it isn't `main`), and `git status --porcelain` is empty.

- [ ] **Step 0b: Confirm `publishToSonatype` is currently absent**

This is the "failing test" baseline for Task 1.

```bash
./gradlew tasks --all 2>/dev/null | grep -E "publishToSonatype|closeAndReleaseSonatypeStagingRepository" || echo "ABSENT (expected)"
```

Expected output: `ABSENT (expected)` (the `grep` finds no matches, so its non-zero exit triggers the `||` echo).

---

## Task 1: Migrate publishing in `build.gradle.kts`

**Files:**
- Modify: `build.gradle.kts` (three coordinated edits: plugins block, signing block, publishing/nexusPublishing blocks)

**Test (gradle smoke):** `./gradlew help` must succeed (script compiles); `./gradlew tasks --all` must list `publishToSonatype` and `closeAndReleaseSonatypeStagingRepository`; `./gradlew build` must still pass.

- [ ] **Step 1: Add the `nexus-publish-plugin` to the `plugins` block**

Edit `build.gradle.kts`. Replace:

```kotlin
plugins {
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka") version "1.9.20"
    kotlin("multiplatform") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
}
```

With:

```kotlin
plugins {
    id("maven-publish")
    id("signing")
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    id("org.jetbrains.dokka") version "1.9.20"
    kotlin("multiplatform") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
}
```

- [ ] **Step 2: Replace the `signing` block with in-memory keys**

Replace:

```kotlin
signing {
    useGpgCmd()
    sign(publishing.publications)
}
```

With:

```kotlin
signing {
    val signingKey: String? = System.getenv("GPG_PRIVATE_KEY")
    val signingPassword: String? = System.getenv("GPG_PASSPHRASE")
    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
    sign(publishing.publications)
}
```

The `if (...)` guard is intentional — it lets local `./gradlew build` runs without GPG env vars skip signing instead of failing.

- [ ] **Step 3: Remove the inner `repositories { maven { ... } }` block from `publishing`**

Inside the existing `publishing { publications { ... } }` block, find and **delete** these lines (the entire `repositories { ... }` block, including its braces):

```kotlin
        repositories {
            maven {
                name="oss"
                val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
                credentials {
                    username = System.getenv("SONATYPE_USERNAME")
                    password = System.getenv("SONATYPE_PASSWORD")
                }
            }
        }
```

After this deletion, the `publishing { publications { ... } }` block should contain only the `withType<MavenPublication> { ... }` portion (with the pom metadata, javadocJar, etc.).

- [ ] **Step 4: Add the top-level `nexusPublishing` block**

After the closing `}` of the `publishing { ... }` block (around the empty line preceding `kotlin {`), insert a blank line then:

```kotlin
nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }
}
```

This block is at the same indentation level as `signing { ... }`, `publishing { ... }`, and `kotlin { ... }` — i.e., column 0.

- [ ] **Step 5: Verify gradle script compiles**

```bash
./gradlew help
```

Expected: command succeeds (exit 0). On first run after adding the new plugin, gradle will download `io.github.gradle-nexus.publish-plugin:2.0.0` from the Gradle Plugin Portal — this can take 10-30 seconds. If the command fails with `Unresolved reference: nexusPublishing`, Step 1 was applied incorrectly (plugin id missing) — fix and re-run. If it fails with `Could not find method useInMemoryPgpKeys()`, Step 2 was misapplied — re-check.

- [ ] **Step 6: Verify the new publish tasks are now exposed**

```bash
./gradlew tasks --all 2>/dev/null | grep -E "publishToSonatype|closeAndReleaseSonatypeStagingRepository"
```

Expected: two lines of output, one starting with `publishToSonatype` and one starting with `closeAndReleaseSonatypeStagingRepository`. Each will be followed by a description string from the plugin (the exact wording is plugin-internal — what matters is the task names themselves are present).

If neither task name appears, Step 4 was misapplied — re-check the `nexusPublishing` block. If `publishToSonatype` appears but `closeAndReleaseSonatypeStagingRepository` does not (or vice versa), the plugin likely loaded but the `sonatype { ... }` repository definition is missing — re-check Step 4.

- [ ] **Step 7: Verify the build still passes end-to-end**

```bash
./gradlew build
```

Expected: BUILD SUCCESSFUL. This runs the full multiplatform build (compiles every host-compatible target on macOS, runs jvm/js/wasm tests). Will take several minutes on a cold cache. If it fails, do not proceed — diagnose using `./gradlew build --stacktrace` and the systematic-debugging skill.

- [ ] **Step 8: Commit**

```bash
git add build.gradle.kts
git commit -m "Migrate publishing to nexus-publish-plugin + Central Portal

- Add io.github.gradle-nexus.publish-plugin 2.0.0
- Replace inner publishing.repositories.maven block with top-level
  nexusPublishing block targeting Central Portal URLs
- Switch signing.useGpgCmd() to useInMemoryPgpKeys reading
  GPG_PRIVATE_KEY/GPG_PASSPHRASE env vars (with guard so local
  builds without those env vars skip signing instead of failing)

Enables ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
for the upcoming deploy.yml workflow.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 2: Rewrite `.github/workflows/build.yml`

**Files:**
- Modify: `.github/workflows/build.yml` (full rewrite — current file is 14 lines, replaced wholesale)

**Test:** YAML syntactically valid; structural sanity (one job, runs on macos-latest, JDK 21, runs `./gradlew build`).

- [ ] **Step 1: Confirm the existing file's current contents**

```bash
cat .github/workflows/build.yml
```

Expected: a 14-line file that triggers on `push:` (any branch) and runs `./gradlew build` on `ubuntu-latest` with no JDK setup. If contents differ significantly, stop and reconcile with the spec before continuing.

- [ ] **Step 2: Replace the file**

Overwrite `.github/workflows/build.yml` with:

```yaml
name: Build

on:
  pull_request:
    branches: [main]

jobs:
  build:
    runs-on: macos-latest

    steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up JDK
      uses: actions/setup-java@v4
      with:
        java-version: '21'
        distribution: 'temurin'

    - name: Setup Gradle
      uses: gradle/actions/setup-gradle@v3

    - name: Build and test
      run: ./gradlew build
```

- [ ] **Step 3: Validate YAML syntax**

```bash
python3 -c "import yaml,sys; yaml.safe_load(open('.github/workflows/build.yml')); print('OK')"
```

Expected: `OK`. If `yaml` module is missing, fall back to:

```bash
ruby -e "require 'yaml'; YAML.load_file('.github/workflows/build.yml'); puts 'OK'"
```

If neither python3-yaml nor ruby is available, do a structural scan instead:

```bash
grep -E "^(name|on|jobs|  build|    runs-on|    steps):" .github/workflows/build.yml
```

Expected: one match for each of `name:`, `on:`, `jobs:`, `  build:`, `    runs-on:`, `    steps:`.

- [ ] **Step 4: Commit**

```bash
git add .github/workflows/build.yml
git commit -m "Rewrite build.yml as kmapper-shaped PR build on macos-latest

Replaces minimal 'on: push' workflow with kmapper-style PR-only build
job. Runs on macos-latest (instead of kmapper's ubuntu-latest) so all
declared KMP targets compile, including Apple-platform targets that
are auto-disabled on Linux hosts.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 3: Create `.github/workflows/deploy.yml`

**Files:**
- Create: `.github/workflows/deploy.yml`

**Test:** YAML syntactically valid; structural sanity (one job on macos-latest, three triggers: push-main / release-published / workflow_dispatch with `version` input).

- [ ] **Step 1: Confirm the file does not already exist**

```bash
test -f .github/workflows/deploy.yml && echo "ALREADY EXISTS — STOP" || echo "absent (expected)"
```

Expected: `absent (expected)`. If the file already exists, stop and reconcile — overwriting silently is wrong.

- [ ] **Step 2: Create the file**

Write `.github/workflows/deploy.yml` with these exact contents:

```yaml
name: Deploy

on:
  push:
    branches: [main]
  release:
    types: [published]
  workflow_dispatch:
    inputs:
      version:
        description: 'Version to deploy'
        required: true
        type: string

jobs:
  deploy:
    runs-on: macos-latest

    steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up JDK
      uses: actions/setup-java@v4
      with:
        java-version: '21'
        distribution: 'temurin'

    - name: Setup Gradle
      uses: gradle/actions/setup-gradle@v3

    - name: Build and test
      run: ./gradlew build

    - name: Set version from input
      if: github.event_name == 'workflow_dispatch'
      run: |
        echo "VERSION=${{ github.event.inputs.version }}" >> $GITHUB_ENV
        sed -i '' 's/version = ".*"/version = "${{ github.event.inputs.version }}"/' build.gradle.kts

    - name: Set version from release
      if: github.event_name == 'release'
      run: |
        echo "VERSION=${{ github.event.release.tag_name }}" >> $GITHUB_ENV
        sed -i '' 's/version = ".*"/version = "${{ github.event.release.tag_name }}"/' build.gradle.kts

    - name: Publish to Maven Central
      env:
        SONATYPE_USERNAME: ${{ secrets.SONATYPE_USERNAME }}
        SONATYPE_PASSWORD: ${{ secrets.SONATYPE_PASSWORD }}
        GPG_PRIVATE_KEY: ${{ secrets.GPG_PRIVATE_KEY }}
        GPG_PASSPHRASE: ${{ secrets.GPG_PASSPHRASE }}
      run: ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
```

Note the `sed -i ''` (empty string after `-i`) — this is BSD-sed (macOS) syntax, intentional because the runner is `macos-latest`. Linux's GNU sed uses `sed -i 's/.../.../'` without the empty arg. Do NOT change this to GNU-sed form.

- [ ] **Step 3: Validate YAML syntax**

```bash
python3 -c "import yaml,sys; yaml.safe_load(open('.github/workflows/deploy.yml')); print('OK')"
```

Expected: `OK`. (Same fallbacks as Task 2 Step 3 if python3-yaml is unavailable.)

- [ ] **Step 4: Structural sanity check on triggers**

```bash
grep -E "^(  push:|  release:|  workflow_dispatch:|    branches:|    types:|    inputs:)" .github/workflows/deploy.yml
```

Expected: at minimum one match for each of `  push:`, `  release:`, `  workflow_dispatch:`. Confirms all three triggers are wired.

- [ ] **Step 5: Commit**

```bash
git add .github/workflows/deploy.yml
git commit -m "Add deploy.yml: publish to Central Portal on main/release/dispatch

New deploy workflow modeled on kmapper's deploy.yml, adapted for KMP:
- Runs on macos-latest so all declared targets (linux/mingw/macos/
  ios/tvos/watchos/jvm/js/wasm) are publishable from a single host
- Three triggers: push to main, release published, manual dispatch
  with version input
- workflow_dispatch and release paths sed the version into
  build.gradle.kts before publishing (BSD-sed syntax for macOS)
- Publishes via ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
  using SONATYPE_USERNAME/PASSWORD and GPG_PRIVATE_KEY/PASSPHRASE
  secrets (must be configured in repo Settings before first run)

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>"
```

---

## Task 4: Final integration check

**Files:** none (read-only verification)

- [ ] **Step 1: Confirm three commits landed on the working branch**

```bash
git log --oneline -n 3
```

Expected (commit hashes will differ): three lines, top-to-bottom matching:
1. `Add deploy.yml: publish to Central Portal on main/release/dispatch`
2. `Rewrite build.yml as kmapper-shaped PR build on macos-latest`
3. `Migrate publishing to nexus-publish-plugin + Central Portal`

- [ ] **Step 2: Confirm working tree is clean**

```bash
git status --porcelain
```

Expected: empty.

- [ ] **Step 3: Smoke test the gradle build one more time**

```bash
./gradlew build
```

Expected: BUILD SUCCESSFUL. Sanity check that the workflow YAML changes (which don't touch gradle) didn't somehow regress anything.

---

## Manual follow-up (out of plan scope, but document for the user)

These cannot be automated by this plan and must be done by a human before the first deploy run:

1. **Configure GitHub repo secrets** under **Settings → Secrets and variables → Actions**:
   - `SONATYPE_USERNAME` — Central Portal user token name
   - `SONATYPE_PASSWORD` — Central Portal user token password
   - `GPG_PRIVATE_KEY` — output of `gpg --armor --export-secret-keys <KEY_ID>` (full ASCII-armored block)
   - `GPG_PASSPHRASE` — passphrase for the above key

2. **Decide push-to-`main` semantics** before merging this branch (see spec Risks section). Currently `version = "0.0.1"` (no `-SNAPSHOT`), so `push: branches: [main]` will attempt to re-publish `0.0.1` as a non-snapshot release on every merge — Sonatype rejects that after the first time. Pick one:
   - (a) Change `version = "0.0.1"` → `version = "0.0.1-SNAPSHOT"` in `build.gradle.kts` so push-to-main publishes snapshots only; reserve real-release publishing for `release` and `workflow_dispatch` paths.
   - (b) Drop the `push: branches: [main]` trigger from `deploy.yml` so deploys only run on `release` events or manual dispatch.

3. **Verify Central Portal namespace migration** for `community.flock.kotlinx.rgxgen`. If the namespace has not been migrated from legacy s01 OSSRH, the first deploy will fail with an authentication or routing error. That's a Sonatype-side operations task, not a code fix.
