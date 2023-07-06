@file:Suppress("DSL_SCOPE_VIOLATION")

import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.dokka)
    id("maven-publish")
}

kotlin {
    jvm()

    android()

    js(IR) {
        moduleName = "cmnav-screens-vm"
        browser()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosArm64()
    macosX64()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                api(projects.cmnavScreens)
                implementation(compose.runtime)
                implementation(libs.coroutines.core)
            }
        }

        getByName("androidMain") {
            dependencies {
                implementation(libs.android.lifecycle.viewmodel)
                implementation(libs.android.navigation.compose)
                implementation(libs.kotlin.serialization.json)
            }
        }

        create("nonAndroidMain") {
            dependsOn(getByName("commonMain"))
            getByName("jvmMain").dependsOn(this)
            getByName("jsMain").dependsOn(this)
            getByName("iosX64Main").dependsOn(this)
            getByName("iosArm64Main").dependsOn(this)
            getByName("iosSimulatorArm64Main").dependsOn(this)
            getByName("macosArm64Main").dependsOn(this)
            getByName("macosX64Main").dependsOn(this)
        }

        getByName("jvmTest") {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(compose.desktop.currentOs)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
                implementation(libs.coroutines.test)
            }
        }

        getByName("androidInstrumentedTest") {
            dependencies {
                implementation(compose.ui)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
                implementation(compose.material)
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
                implementation(libs.android.test.core)
                implementation(libs.android.test.runner)
            }
        }
    }
}

android {
    compileSdk = 33
    namespace = "io.github.lukwol.cmnav.screens.vm"

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    dependencies {
        debugImplementation(libs.android.test.ui.manifest.compose)
    }
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = "io.github.lukwol"
                artifactId = "cmnav-screens-vm"
                version = "0.1.0"
            }
        }
    }
}
