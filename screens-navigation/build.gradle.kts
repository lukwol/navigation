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

//    iosArm32() dokka fails
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    linuxX64()
    macosX64()
    macosArm64()
    mingwX64()
    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    watchosX86()
    watchosX64()
    watchosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.web.testUtils)
            }
        }
    }
}

compose.kotlinCompilerPlugin.set(libs.versions.compose.compiler)
