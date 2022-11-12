@file:Suppress("UNUSED_VARIABLE", "DSL_SCOPE_VIOLATION", "OPT_IN_IS_NOT_ENABLED")

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.dokka)
    id("maven-publish")
}

kotlin {
    jvm {
        withJava()
    }
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }

    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":viewmodel"))
                api(project(":screens-navigation"))
                implementation(compose.runtime)
                implementation(libs.coroutines.core)
            }
        }
        val commonTest by getting
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTestJUnit4)
                implementation(libs.kotest.assertions.core)
            }
        }
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}

compose.kotlinCompilerPlugin.set(libs.versions.compose.compiler)
