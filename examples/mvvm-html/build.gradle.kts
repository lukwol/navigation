@file:Suppress("DSL_SCOPE_VIOLATION")

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
        getByName("jsMain") {
            dependencies {
                implementation(compose.html.core)
                implementation(projects.viewmodelScreensNavigation)
            }
        }
    }
}
