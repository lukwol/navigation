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

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosArm64()
    macosX64()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(compose.runtime)
            }
        }
        getByName("jvmMain") {
            dependencies {
                implementation(compose.animation)
            }
        }
        create("nativeMain") {
            dependsOn(getByName("commonMain"))
            getByName("iosX64Main").dependsOn(this)
            getByName("iosArm64Main").dependsOn(this)
            getByName("iosSimulatorArm64Main").dependsOn(this)
            getByName("macosArm64Main").dependsOn(this)
            getByName("macosX64Main").dependsOn(this)
            dependencies {
                implementation(compose.animation)
            }
        }
        create("sharedTest") {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
            }
        }
        getByName("jvmTest") {
            dependsOn(getByName("sharedTest"))
            dependencies {
                implementation(compose.desktop.currentOs)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
            }
        }
        getByName("jsTest") {
            dependsOn(getByName("sharedTest"))
            dependencies {
                implementation(compose.html.core)
                implementation(compose.html.testUtils)
            }
        }
    }
}
