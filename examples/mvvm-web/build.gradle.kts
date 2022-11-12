@file:Suppress("UNUSED_VARIABLE", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(project(":viewmodel-screens-navigation"))
            }
        }
        val jsTest by getting
    }
}

compose.kotlinCompilerPlugin.set(libs.versions.compose.compiler)
