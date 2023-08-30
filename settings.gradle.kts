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
}

include(":navigation-screens")
include(":navigation-screens-viewmodel")
include(":navigation-windows")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
