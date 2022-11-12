@file:Suppress("UNUSED_VARIABLE", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.dokka)
    id("maven-publish")
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.junit4)
                implementation(libs.coroutines.test)
                implementation(libs.kotest.assertions.core)
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(libs.versions.compose.compiler)
}
