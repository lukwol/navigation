@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.dokka)
    id("maven-publish")
}

kotlin {
    jvm()

    js(IR) {
        browser()
    }

    linuxX64()
    mingwX64()
    macosX64()
    macosArm64()
    ios()
    iosSimulatorArm64()
    tvos()
    tvosSimulatorArm64()
    watchos()
    watchosSimulatorArm64()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(compose.runtime)
            }
        }
        getByName("commonTest") {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        getByName("jvmMain") {
            dependencies {
                implementation(compose.animation)
            }
        }
        getByName("jvmTest") {
            dependencies {
                implementation(compose.desktop.currentOs)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
            }
        }
        getByName("jsTest") {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.html.testUtils)
            }
        }

        create("nativeMain") {
            dependsOn(getByName("commonMain"))
            getByName("linuxX64Main").dependsOn(this)
            getByName("mingwX64Main").dependsOn(this)
            getByName("macosX64Main").dependsOn(this)
            getByName("macosArm64Main").dependsOn(this)
            getByName("iosMain").dependsOn(this)
            getByName("iosSimulatorArm64Main").dependsOn(this)
            getByName("tvosMain").dependsOn(this)
            getByName("tvosSimulatorArm64Main").dependsOn(this)
            getByName("watchosMain").dependsOn(this)
            getByName("watchosSimulatorArm64Main").dependsOn(this)
        }
    }
}
