@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

kotlin {
    android {
        dependencies {
            implementation("androidx.core:core-ktx:1.10.0")
            implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
            implementation("androidx.activity:activity-compose:1.7.0")
            implementation("androidx.appcompat:appcompat:1.6.1")

            implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
            implementation("com.google.dagger:dagger:${libs.versions.hilt.get()}")
            implementation("com.google.dagger:hilt-android:${libs.versions.hilt.get()}")
            configurations.getByName("kapt").dependencies.addAll(
                listOf(
                    DefaultExternalModuleDependency(
                        "com.google.dagger",
                        "dagger-compiler",
                        libs.versions.hilt.get()
                    ),
                    DefaultExternalModuleDependency(
                        "com.google.dagger",
                        "hilt-android-compiler",
                        libs.versions.hilt.get()
                    ),
                    DefaultExternalModuleDependency(
                        "androidx.hilt",
                        "hilt-compiler",
                        "1.0.0"
                    )
                )
            )
            implementation(projects.viewmodelScreensNavigation)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.uiTooling)
            implementation(compose.preview)
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
