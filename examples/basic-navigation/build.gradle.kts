@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.application)
}

kotlin {
    jvm()

    android()

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(compose.runtime)
                implementation(projects.screensNavigation)
            }
        }

        create("nonHtmlMain") {
            dependsOn(getByName("commonMain"))
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
            }
        }

        getByName("jvmMain") {
            dependsOn(getByName("nonHtmlMain"))
            dependencies {
                implementation(compose.uiTooling)
                implementation(compose.preview)
            }
        }

        getByName("androidMain") {
            dependsOn(getByName("nonHtmlMain"))
            dependencies {
                implementation("androidx.core:core-ktx:1.10.0")
                implementation("androidx.activity:activity-compose:1.7.0")
                implementation("androidx.appcompat:appcompat:1.6.1")
            }
        }

        getByName("jsMain") {
            dependencies {
                implementation(compose.html.core)
            }
        }
    }
}

compose {
    desktop {
        application {
            mainClass = "io.github.lukwol.examples.MainKt"
        }
    }
}


android {
    compileSdk = 33
    namespace = "io.github.lukwol.example"

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }
}
