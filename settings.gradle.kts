import org.gradle.kotlin.dsl.create

rootProject.name = "ai-example-service"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

include("app")
