import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka") version "1.8.10"
    kotlin("multiplatform") version "1.9.23"
    kotlin("plugin.serialization") version "1.5.21"
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
    useGpgCmd()
    sign(publishing.publications)
}

publishing {
    publications {
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


kotlin {
    macosX64()
    macosArm64()
    linuxX64()
    mingwX64()
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
    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
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

