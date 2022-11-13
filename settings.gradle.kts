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

include(":examples:basic-single-window")
include(":examples:mvvm-multi-window")
include(":examples:mvvm-web")
