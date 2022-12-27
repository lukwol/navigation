@file:Suppress("UNUSED_VARIABLE", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.dokka)
    id("maven-publish")
}

kotlin {
    jvm()

    js(IR) {
        browser()
    }

    linuxX64()
    mingwX64()
    macosX64()
    macosArm64()
    ios()
    iosSimulatorArm64()
    tvos()
    tvosSimulatorArm64()
    watchos()
    watchosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutines.core)
            }
        }
    }
}
