@file:Suppress("UnstableApiUsage")

rootProject.name = "cm-navigation"

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

include(":examples:basic-single-window")
include(":examples:mvvm-html")
include(":examples:mvvm-android")
include(":examples:mvvm-ios")
include(":examples:mvi-desktop")
include(":examples:mvi-multi-window")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
