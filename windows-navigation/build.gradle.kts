@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.dokka)
    id("maven-publish")
}

dependencies {
    implementation(compose.desktop.currentOs)

    testImplementation(libs.junit4)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.kotest.assertions.core)
}

publishing {
    publications {
        create<MavenPublication>("jvm") {
            artifactId = "windows-navigation-jvm"

            from(components["java"])
        }
    }
}

compose {
    kotlinCompilerPlugin.set(libs.versions.compose.compiler)
}
