import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("maven-publish")
    id("signing")
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    id("org.jetbrains.dokka") version "1.9.20"
    kotlin("multiplatform") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
}

group = "community.flock.kotlinx.rgxgen"
version = "0.0.1"


val dokkaOutputDir = "$buildDir/dokka"

tasks.getByName<DokkaTask>("dokkaHtml") {
    outputDirectory.set(file(dokkaOutputDir))
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}

val javadocJar = tasks.register<Jar>("javadocJar") {
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaOutputDir)
}

repositories {
    mavenCentral()
}

signing {
    val signingKey: String? = System.getenv("GPG_PRIVATE_KEY")
    val signingPassword: String? = System.getenv("GPG_PASSPHRASE")
    isRequired = signingKey != null && signingPassword != null
    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
    sign(publishing.publications)
}

publishing {
    publications {
        withType<MavenPublication> {
            artifact(javadocJar)
            pom {
                name.set("Flock. community")
                description.set("Kotlin rgxgen")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                url.set("https://flock.community")
                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/flock-community/kotlin-rgxgen/issues")
                }
                scm {
                    connection.set("https://github.com/flock-community/kotlin-rgxgen.git")
                    url.set("https://github.com/flock-community/kotlin-rgxgen")
                }
                developers {
                    developer {
                        name.set("Willem Veelenturf")
                        email.set("willem.veelenturf@flock.community")
                    }
                }
            }
        }
    }
}

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


kotlin {
    macosX64()
    macosArm64()
    linuxX64()
    linuxArm64()
    mingwX64()
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX64()
    androidNativeX86()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()
    watchosSimulatorArm64()
    watchosDeviceArm64()
    jvm {
        compilations.all {
            kotlinOptions{
                jvmTarget = "17"
            }
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        nodejs {
            testTask {
                useMocha()
            }
        }
        binaries.executable()
    }
    wasmJs {
        nodejs()
        binaries.executable()
    }
    wasmWasi {
        nodejs()
        binaries.executable()
    }
    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jsMain by getting {
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val jvmMain by getting {
        }
        val jvmTest by getting  {
            dependencies {
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
                implementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
                implementation("org.junit.platform:junit-platform-suite:1.10.2")
                implementation("org.openjdk.jmh:jmh-core:1.37")
                implementation("org.openjdk.jmh:jmh-generator-annprocess:1.37")
            }
        }
    }
}

