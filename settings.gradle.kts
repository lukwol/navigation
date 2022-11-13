rootProject.name = "cm-navigation"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

include(":screens-navigation")
include(":viewmodel")
include(":viewmodel-screens-navigation")
include(":windows-navigation")
include(":examples:basic-desktop")
include(":examples:mvvm-web")
