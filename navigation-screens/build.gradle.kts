import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(commonLibs.plugins.kotlin.multiplatform)
    alias(commonLibs.plugins.compose.multiplatform)
    alias(androidLibs.plugins.library)
    alias(commonLibs.plugins.kotlin.serialization)
}

kotlin {
    applyDefaultHierarchyTemplate()

//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser()
//    }

    jvm("desktop")

    androidTarget {
        publishLibraryVariants("release")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.animation)
            }
        }
        androidMain {
            dependencies {
                implementation(androidLibs.compose.navigation)
                implementation(commonLibs.kotlin.serialization.json)
            }
        }
        create("nonAndroidMain") {
            dependsOn(getByName("commonMain"))
        }
        getByName("desktopMain") {
            dependsOn(getByName("nonAndroidMain"))
        }
        iosMain {
            dependsOn(getByName("nonAndroidMain"))
        }
//        getByName("wasmJsMain") {
//            dependsOn(getByName("nonAndroidMain"))
//        }
        getByName("androidInstrumentedTest").dependencies {
            implementation(kotlin("test"))
            implementation(compose.material3)
            implementation(commonLibs.kotlin.serialization.json)
            implementation(androidLibs.test.runner)
            implementation(androidLibs.compose.ui.test.junit4)
        }
        getByName("desktopTest").dependencies {
            implementation(kotlin("test"))
            implementation(compose.material3)
            implementation(compose.desktop.currentOs)
            implementation(compose.desktop.uiTestJUnit4)
        }
    }
}

android {
    compileSdk = androidLibs.versions.compile.sdk.get().toInt()
    namespace = "io.github.lukwol.navigation.screens"

    defaultConfig {
        minSdk = androidLibs.versions.min.sdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = androidLibs.versions.compose.compiler.get()
    }

    dependencies {
        debugImplementation(androidLibs.compose.ui.test.manifest)
    }
}
