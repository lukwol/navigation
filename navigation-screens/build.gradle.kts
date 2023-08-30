import BuildConstants.Modules.Namespace

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlin.serialization)
    id("maven-publish")
}

kotlin {
    jvm()

    androidTarget {
        publishLibraryVariants("release")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(compose.runtime)
            }
        }

        getByName("androidMain") {
            dependencies {
                implementation(compose.animation)
                implementation(libs.android.navigation.compose)
                implementation(libs.kotlin.serialization.json)
            }
        }

        create("nativeMain") {
            dependsOn(getByName("commonMain"))
            getByName("iosX64Main").dependsOn(this)
            getByName("iosArm64Main").dependsOn(this)
            getByName("iosSimulatorArm64Main").dependsOn(this)
        }

        create("nonJsMain") {
            dependsOn(getByName("commonMain"))
            getByName("jvmMain").dependsOn(this)
            getByName("androidMain").dependsOn(this)
            getByName("nativeMain").dependsOn(this)
            dependencies {
                implementation(compose.animation)
            }
        }

        create("nonAndroidMain") {
            dependsOn(getByName("commonMain"))
            getByName("jvmMain").dependsOn(this)
            getByName("nativeMain").dependsOn(this)
        }

        create("nativeJvmMain") {
            dependsOn(getByName("nonAndroidMain"))
            dependsOn(getByName("nonJsMain"))
            getByName("jvmMain").dependsOn(this)
            getByName("nativeMain").dependsOn(this)
        }

        getByName("jvmTest") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(compose.desktop.uiTestJUnit4)
                implementation(compose.material)
                implementation(libs.kotlin.test)
            }
        }

        getByName("androidInstrumentedTest") {
            dependencies {
                implementation(compose.ui)
                implementation(compose.desktop.uiTestJUnit4)
                implementation(compose.material)
                implementation(libs.kotlin.test)
                implementation(libs.android.test.core)
                implementation(libs.android.test.runner)
            }
        }
    }
}

android {
    compileSdk = BuildConstants.Android.CompileSdk
    namespace = BuildConstants.Modules.NavigationScreens.Namespace

    defaultConfig {
        minSdk = BuildConstants.Android.MinSdk
        testInstrumentationRunner = BuildConstants.Android.TestInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = BuildConstants.Android.JavaVersion
        targetCompatibility = BuildConstants.Android.JavaVersion
    }

    dependencies {
        debugImplementation(libs.android.test.ui.manifest.compose)
    }
}
