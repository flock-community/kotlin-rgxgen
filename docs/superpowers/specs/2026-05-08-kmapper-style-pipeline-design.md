# Pipeline like kmapper, adapted for kotlin-rgxgen (KMP)

**Status:** approved (design phase)
**Date:** 2026-05-08
**Reference:** [flock-community/kmapper](https://github.com/flock-community/kmapper) `.github/workflows/{build,deploy}.yml`

## Goal

Replace the current minimal `build.yml` workflow with a kmapper-shaped two-workflow CI/CD pipeline (`build.yml` for PR validation, `deploy.yml` for releases), adapted for this project's Kotlin Multiplatform setup and migrated publishing.

## Non-goals

- A `benchmarks` job. kmapper's `build.yml` has one that publishes three modules to mavenLocal and runs a JMH threshold gate against a `:benchmarks` module. kotlin-rgxgen has JMH dependencies in `jvmTest` but no `:benchmarks` module and no threshold-gate task. Adding one is out of scope.
- Multi-OS matrix builds. The deploy job runs on `macos-latest` (single host can build every declared target); the PR build runs on `macos-latest` for parity with deploy.
- Changing source code, tests, `settings.gradle.kts`, `gradlew`, or `gradle.properties`.
- Migrating the Sonatype namespace to Central Portal. The build will *target* the Central Portal URLs; whether the namespace has actually been migrated is a separate operations task and the first deploy will validate it.

## Decisions

| Decision | Choice | Why |
|---|---|---|
| Pipeline shape | Structural mirror of kmapper, adapted for KMP | User preference — same two-workflow shape minus benchmarks. |
| Deploy runner | `macos-latest`, single host | macOS can build every target this project declares (linux\*, mingwX64, macos\*, ios\*, tvos\*, watchos\*, jvm, js, wasm\*). One host → one publish command → all artifacts. |
| PR build runner | `macos-latest` | Matches deploy → PR exercises every target that will be published. Avoids regressions in Apple targets only being caught at release time. |
| Sonatype host | Central Portal (matches kmapper) | Future-proof; legacy s01 OSSRH is on a deprecation timeline. |
| JDK version | 21 (temurin, matches kmapper) | Compiles fine; bytecode level is controlled by `jvmTarget = "17"` in `build.gradle.kts` regardless of build JDK. |
| GPG signing | In-memory keys (matches kmapper) | Switch from `signing.useGpgCmd()` to `signing.useInMemoryPgpKeys(...)` reading `GPG_PRIVATE_KEY` / `GPG_PASSPHRASE` env vars. Avoids needing gpg CLI on the runner. |

## Files touched

| File | Action |
|---|---|
| `.github/workflows/build.yml` | Rewrite |
| `.github/workflows/deploy.yml` | Create |
| `build.gradle.kts` | Edit (publishing migration) |

## Component 1 — `build.gradle.kts` publishing migration

Three coordinated changes; preserves all existing pom metadata, javadocJar, dokka task wiring, group, and version.

### 1a. Add the nexus-publish-plugin

In the top-level `plugins { ... }` block, add:

```kotlin
id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
```

Single-module project, so the plugin is applied at the root and that's the only place it goes.

### 1b. Replace the manual staging repo with `nexusPublishing`

**Remove** the inner `repositories { maven { name="oss"; ... } }` block from inside `publishing { publications { repositories { ... } } }`.

**Add** at top level (alongside `signing`, `publishing`):

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

The plugin auto-injects a staging Maven repo into every `MavenPublication` and provides the `publishToSonatype` and `closeAndReleaseSonatypeStagingRepository` tasks. It also handles releases-vs-snapshots URL switching automatically based on whether the version ends in `-SNAPSHOT`.

### 1c. Switch GPG to in-memory keys

Replace the existing `signing { useGpgCmd(); sign(publishing.publications) }` block with:

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

The `if (...)` guard lets local builds without GPG env vars skip signing instead of failing.

## Component 2 — `.github/workflows/build.yml` (rewrite)

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

Differences from kmapper's `build.yml`: runner is `macos-latest`; no `benchmarks` job. Otherwise identical.

## Component 3 — `.github/workflows/deploy.yml` (new)

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

Differences from kmapper's `deploy.yml`: runner is `macos-latest` instead of `ubuntu-latest`; `sed -i '' '...'` (BSD sed, takes empty backup-suffix arg) instead of GNU sed's `sed -i 's/.../.../'`. Push-to-`main` keeps the version literal currently in `build.gradle.kts` (no sed step on that path); `release` and `workflow_dispatch` paths overwrite the version line in-place before publishing.

## Required GitHub repo secrets

Configured under **Settings → Secrets and variables → Actions** before the first deploy run. This is documentation, not something the implementation creates:

| Secret | Source |
|---|---|
| `SONATYPE_USERNAME` | Central Portal user token name |
| `SONATYPE_PASSWORD` | Central Portal user token password |
| `GPG_PRIVATE_KEY` | Output of `gpg --armor --export-secret-keys <KEY_ID>` (full ASCII-armored block, including `BEGIN/END PGP PRIVATE KEY BLOCK` lines) |
| `GPG_PASSPHRASE` | Passphrase for the above key |

## Verification strategy

This is pipeline / build-config work — no automated tests are added or expected.

1. **Local gradle compile:** `./gradlew help` (or `./gradlew tasks`) on a developer machine after the gradle changes — confirms the script compiles and `publishToSonatype` / `closeAndReleaseSonatypeStagingRepository` tasks are now resolvable.
2. **PR build:** open a PR → `Build` workflow runs on `macos-latest` and passes `./gradlew build`.
3. **First deploy:** push to `main` triggers `Deploy`; staging publish to Central Portal is the actual end-to-end validation — there is no offline way to verify Central Portal credentials, namespace migration status, or signing key acceptance. If the first deploy fails on credentials/namespace, that is operations follow-up, not a defect in this design.

## Risks & open issues

- **Push-to-`main` will re-publish the same release version.** Current `build.gradle.kts` has `version = "0.0.1"` (no `-SNAPSHOT` suffix). With `on: push: branches: [main]` triggering deploy, every merge attempts to publish `0.0.1` as a non-snapshot release — Sonatype rejects that after the first time. kmapper avoids this because its version is `0.0.0-SNAPSHOT`, so push-to-main publishes snapshots. Two options the user should pick before first deploy: (a) change the version in `build.gradle.kts` to `0.0.1-SNAPSHOT` so push-to-main publishes snapshots and only `release`/`workflow_dispatch` publish real releases, or (b) drop `push: branches: [main]` from `deploy.yml`'s triggers and only release via tag/dispatch. **This is a configuration choice for the user, not a design defect — flagged here so the implementation step doesn't surprise them.**
- **Central Portal namespace migration unverified.** If `community.flock.kotlinx.rgxgen` has not been migrated from legacy OSSRH to the Central Portal, the first deploy will fail with an authentication or routing error. Mitigation: design assumes migration is or will be done out-of-band; first deploy surfaces the gap if it exists.
- **macOS runner cost.** macOS GitHub-hosted runners bill at 10x the Linux rate on private repos. This repo appears public (no concrete check done), so cost is moot — but flagging it.
- **`sed -i ''` is BSD-specific.** The version-injection steps are BSD-sed (macOS) syntax. They will fail on a Linux runner. This is intentional given the macos-latest decision; if the runner ever moves back to ubuntu, those two `sed` commands need to drop the `''` argument.
