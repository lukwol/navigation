@file:Suppress("UnstableApiUsage")

rootProject.name = "navigation"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }

    versionCatalogs {
        create("commonLibs") {
            from(files("gradle/common.versions.toml"))
        }
        create("androidLibs") {
            from(files("gradle/android.versions.toml"))
        }
    }
}

include(":navigation-screens")
include(":navigation-screens-viewmodel")
include(":navigation-windows")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
