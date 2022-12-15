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

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.viewmodel)
                api(projects.screensNavigation)
                implementation(compose.runtime)
                implementation(libs.coroutines.core)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(compose.desktop.currentOs)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
            }
        }
    }
}

compose.kotlinCompilerPlugin.set(libs.versions.compose.compiler)
