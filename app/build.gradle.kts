plugins {
    alias(libs.plugins.kotlin)
    application
}

dependencies {
    testImplementation(libs.bundles.test)
}

application {
    mainClass.set("ru.berte.news.AppKt")
}
