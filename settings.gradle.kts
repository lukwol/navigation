@file:Suppress("UnstableApiUsage")

rootProject.name = "cmnav"

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

include(":cmnav-screens")
include(":cmnav-screens-vm")
include(":cmnav-windows")

include(":examples:basic")
include(":examples:mvvm")
include(":examples:mvi-multi-window")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
