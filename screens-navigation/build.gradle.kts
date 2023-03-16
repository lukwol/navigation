@file:Suppress("UNUSED_VARIABLE", "DSL_SCOPE_VIOLATION", "OPT_IN_IS_NOT_ENABLED")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
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
        named("commonMain") {
            dependencies {
                implementation(compose.runtime)
            }
        }
        named("commonTest") {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        named("jvmMain") {
            dependencies {
                implementation(compose.animation)
            }
        }
        named("jvmTest") {
            dependencies {
                implementation(compose.desktop.currentOs)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
            }
        }
        named("jsTest") {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.web.testUtils)
            }
        }
    }
}
