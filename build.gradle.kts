import org.gradle.api.JavaVersion.VERSION_23

import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED

plugins {
    alias(libs.plugins.kotlin)
}

private val javaVersion = VERSION_23

private val modules = project.subprojects.map { it.layout.buildDirectory.get().asFile }


project.allprojects {
    group = "ru.berte.news"
    // VERSION from ci cd mrs and master, git describes for local
    // Eliminates version conflicts, ensures seamless integration with git pipelines and allows local versions
    version = System.getenv("VERSION") ?: ProcessBuilder("git", "describe", "--tags", "--always")
        .start()
        .inputStream
        .bufferedReader()
        .readText()
        .trim()
}

project.subprojects {
    prepareModule(this)
}

/**
 * Sets up a module for compilation and publishing. This function should be called inside a module's project definition.
 *
 * @param project the module to be configured
 */
private fun prepareModule(project: Project) = project.afterEvaluate {
    kotlin {
        jvmToolchain(javaVersion.majorVersion.toInt())
        sourceSets.all {
            languageSettings {
                compilerOptions.freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.uuid.ExperimentalUuidApi")
            }
        }
    }

    java {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    tasks {
        test {
            // EnableDynamicAgentLoading - for mockk agent with java 21 https://github.com/mockk/mockk/issues/1171
            // Xshare - some test lib tries to use cds but cannot do it - jvm uses it only for some boot classes
            // like default java ones.
            // So not to get a warning we turn it off completely. It makes tests a little slower
            // only in case of parallel execution in several different processes
            // (which does not happen at all in ci or locally)
            jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")

            useJUnitPlatform()
            testLogging {
                events = setOf(FAILED, PASSED, SKIPPED)
                exceptionFormat = FULL
                showExceptions = true
                showCauses = true
                showStackTraces = true
                showStandardStreams = true
            }
        }
    }
}
