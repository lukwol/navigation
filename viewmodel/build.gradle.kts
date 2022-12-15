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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.coroutines.swing)
            }
        }
    }
}
