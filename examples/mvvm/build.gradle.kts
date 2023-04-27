@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.application)
    kotlin("native.cocoapods")
}

kotlin {
    jvm()

    android()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(compose.runtime)
                implementation(projects.viewmodelScreensNavigation)
            }
        }

        create("nonJsMain") {
            dependsOn(getByName("commonMain"))
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
            }
        }

        getByName("jvmMain") {
            dependsOn(getByName("nonJsMain"))
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        getByName("androidMain") {
            dependsOn(getByName("nonJsMain"))
            dependencies {
                implementation(libs.android.core)
                implementation(libs.android.appcompat)
                implementation(libs.android.activity.compose)
                implementation(libs.android.navigation.compose)
            }
        }

        create("nonAndroidMain") {
            dependsOn(getByName("commonMain"))
            getByName("jvmMain").dependsOn(this)
            getByName("jsMain").dependsOn(this)
            getByName("iosX64Main").dependsOn(this)
            getByName("iosArm64Main").dependsOn(this)
            getByName("iosSimulatorArm64Main").dependsOn(this)
            dependencies {
                implementation(compose.runtime)
            }
        }

        create("iosMain") {
            dependsOn(getByName("commonMain"))
            dependsOn(getByName("nonJsMain"))
            getByName("iosX64Main").dependsOn(this)
            getByName("iosArm64Main").dependsOn(this)
            getByName("iosSimulatorArm64Main").dependsOn(this)
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material)
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

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    cocoapods {
        version = "1.0.0"
        summary = "Example iOS app with basic navigation"
        homepage = "https://github.com/lukwol/cm-navigation"
        ios.deploymentTarget = "14.1"
        podfile = project.file("iosApp/Podfile")
        framework {
            baseName = "iosMain"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
}
