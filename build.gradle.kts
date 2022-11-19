@file:Suppress("DSL_SCOPE_VIOLATION")

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.dependency.updates)
}

allprojects {
    apply {
        plugin("org.jmailen.kotlinter")
    }

    repositories {
        mavenCentral()
    }

    group = "io.github.lukwol"
    version = "0.1.2"

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    kotlinter {
        disabledRules = arrayOf("no-wildcard-imports")
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        val version = candidate.version.toLowerCaseAsciiOnly()

        listOf("-alpha", "-beta", "-rc")
            .any { it in version }
    }
}
