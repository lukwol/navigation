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

include(":screens-navigation")
include(":viewmodel")
include(":viewmodel-screens-navigation")
include(":windows-navigation")

include(":examples:basic")
include(":examples:mvvm")
include(":examples:mvi-multi-window")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
