plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    jvm()

    sourceSets {
        getByName("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(projects.navigationScreensViewmodel)
                implementation(projects.navigationWindows)
            }
        }
    }
}

compose {
    desktop {
        application {
            mainClass = "${BuildConstants.Group}.examples.MainKt"
        }
    }
}
