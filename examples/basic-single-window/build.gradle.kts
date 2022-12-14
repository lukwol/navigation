@file:Suppress("UNUSED_VARIABLE", "DSL_SCOPE_VIOLATION")

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(projects.screensNavigation)
            }
        }
        val jvmTest by getting
    }
}

compose {
    kotlinCompilerPlugin.set(libs.versions.compose.compiler)

    desktop {
        application {
            mainClass = "io.github.lukwol.examples.MainKt"
            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "BasicSingleWindow"
                packageVersion = "1.0.0"
            }
        }
    }
}
