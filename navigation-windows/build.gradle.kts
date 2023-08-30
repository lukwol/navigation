plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.dokka)
    id("maven-publish")
}

kotlin {
    jvm()

    sourceSets {
        create("desktopMain") {
            getByName("jvmMain").dependsOn(this)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        create("desktopTest") {
            getByName("jvmTest").dependsOn(this)
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
