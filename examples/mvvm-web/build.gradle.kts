@file:Suppress("UNUSED_VARIABLE", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        named("jsMain") {
            dependencies {
                implementation(compose.web.core)
                implementation(projects.viewmodelScreensNavigation)
            }
        }
    }
}
