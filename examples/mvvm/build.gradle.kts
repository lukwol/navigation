plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.application)
    kotlin("native.cocoapods")
}

kotlin {
    jvm()

    androidTarget()

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
                implementation(projects.navigationScreensViewmodel)
            }
        }

        create("nonJsMain") {
            dependsOn(getByName("commonMain"))
            dependencies {
                implementation(compose.ui)
                implementation(compose.material)
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
            mainClass = "${BuildConstants.Group}.examples.MainKt"
        }
    }
}


android {
    compileSdk = BuildConstants.Android.CompileSdk
    namespace = "${BuildConstants.Group}.examples"

    defaultConfig {
        minSdk = BuildConstants.Android.MinSdk
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    compileOptions {
        sourceCompatibility = BuildConstants.Android.JavaVersion
        targetCompatibility = BuildConstants.Android.JavaVersion
    }
}

kotlin {
    cocoapods {
        version = "1.0.0"
        summary = "Example iOS app with navigation utilizing View Models"
        homepage = "https://github.com/lukwol/navigation"
        ios.deploymentTarget = BuildConstants.IOS.ExamplesDeploymentTarget
        podfile = project.file("iosApp/Podfile")
        framework {
            baseName = "iosMain"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
}
