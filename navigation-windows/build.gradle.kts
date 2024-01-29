plugins {
    alias(commonLibs.plugins.kotlin.multiplatform)
    alias(commonLibs.plugins.compose.multiplatform)
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvm("desktop")

    sourceSets {
        getByName("desktopMain").dependencies {
            implementation(compose.desktop.currentOs)
        }
        getByName("desktopTest").dependencies {
            implementation(kotlin("test"))
        }
    }
}
