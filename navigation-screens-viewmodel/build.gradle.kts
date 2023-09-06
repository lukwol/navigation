import BuildConstants.Modules.Namespace

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.dokka)
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
                api(projects.navigationScreens)

                implementation(compose.animation)
                implementation(libs.coroutines.core)
            }
        }
        getByName("androidMain") {
            dependencies {
                implementation(libs.lifecycle.viewmodel.android)
                implementation(libs.compose.navigation.android)
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
                implementation(libs.kotlin.serialization.json)
                implementation(compose.material3)
            }
        }
        create("composeUiTest") {
            dependsOn(getByName("commonTest"))
            dependencies {
                implementation(compose.desktop.uiTestJUnit4)
            }
        }
        getByName("androidInstrumentedTest") {
            dependsOn(getByName("composeUiTest"))
            dependencies {
                implementation(libs.test.runner.android)
            }
        }
        create("desktopTest") {
            dependsOn(getByName("composeUiTest"))
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
    namespace = BuildConstants.Modules.NavigationScreensViewModel.Namespace

    defaultConfig {
        minSdk = BuildConstants.Android.MinSdk
        testInstrumentationRunner = BuildConstants.Android.TestInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = BuildConstants.JavaVersion
        targetCompatibility = BuildConstants.JavaVersion
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    dependencies {
        debugImplementation(libs.compose.ui.test.manifest.android)
    }
}
