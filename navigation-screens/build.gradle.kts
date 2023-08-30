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
    @Suppress("OPT_IN_USAGE")
    targetHierarchy.default()

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
                implementation(compose.animation)
            }
        }
        getByName("androidMain") {
            dependencies {
                implementation(libs.android.navigation.compose)
                implementation(libs.kotlin.serialization.json)
            }
        }
        create("nonAndroidMain") {
            dependsOn(getByName("commonMain"))
        }
        create("desktopMain") {
            getByName("jvmMain").dependsOn(this)
            dependsOn(getByName("nonAndroidMain"))
        }
        getByName("iosMain") {
            dependsOn(getByName("nonAndroidMain"))
        }
        getByName("commonTest") {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        create("nonNativeTest") {
            dependsOn(getByName("commonTest"))
            dependencies {
                implementation(libs.kotlin.serialization.json)
                implementation(compose.material3)
                implementation(compose.desktop.uiTestJUnit4)
            }
        }
        getByName("androidInstrumentedTest") {
            dependsOn(getByName("nonNativeTest"))
            dependencies {
                implementation(libs.android.test.runner)
            }
        }
        create("desktopTest") {
            dependsOn(getByName("nonNativeTest"))
            getByName("jvmTest").dependsOn(this)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        getByName("iosTest")
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
