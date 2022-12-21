@file:Suppress("UnstableApiUsage")

rootProject.name = "cm-navigation"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}

include(":screens-navigation")
include(":viewmodel")
include(":viewmodel-screens-navigation")
include(":windows-navigation")

include(":examples:basic-single-window")
include(":examples:mvvm-web")
include(":examples:mvi-multi-window")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
