plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvm()

    sourceSets {
        create("desktopMain") {
            jvmMain.get().dependsOn(this)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        create("desktopTest") {
            jvmTest.get().dependsOn(this)
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
