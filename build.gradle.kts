import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly

plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlinter) apply false
    alias(libs.plugins.dokka)
    alias(libs.plugins.dependency.updates)
}

subprojects {
    apply {
        plugin("org.jetbrains.dokka")
        plugin("org.jmailen.kotlinter")
        plugin("maven-publish")
        plugin("signing")
    }

    group = BuildConstants.Group
    version = BuildConstants.VersionName

    extensions.configure<PublishingExtension> {
        repositories {
            maven {
                name = "Staging"
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = findProperty("ossrhUsername").toString()
                    password = findProperty("ossrhPassword").toString()
                }
            }
            maven {
                name = "Snapshots"
                setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                credentials {
                    username = findProperty("ossrhUsername").toString()
                    password = findProperty("ossrhPassword").toString()
                }
            }
        }

        extensions.configure<SigningExtension> {
            useInMemoryPgpKeys(
                /* defaultKeyId = */ findProperty("keyId").toString(),
                /* defaultSecretKey = */ findProperty("keyBase64").toString(),
                /* defaultPassword = */ findProperty("keyPassword").toString()
            )
        }

        val javadocJar by tasks.registering(Jar::class) {
            archiveClassifier.set("javadoc")
        }

        publications {
            withType<MavenPublication> {
//                artifact(javadocJar)

                pom {
                    name.set(project.name)
                    description.set("Simple navigation in Compose Multiplatform apps")
                    url.set("https://github.com/lukwol/navigation")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://github.com/lukwol/navigation/blob/main/LICENSE")
                        }
                    }
                    scm {
                        url.set("https://github.com/lukwol/navigation")
                        connection.set("scm:git:git://github.com/lukwol/navigation.git")
                    }
                    developers {
                        developer {
                            name.set("Lukasz Wolanczyk")
                            url.set("https://github.com/lukwol")
                        }
                    }
                }

                the<SigningExtension>().sign(this)
            }
        }
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            if (project.findProperty("enableComposeCompilerReports") == "true") {
                val destinationDir = project.layout.buildDirectory.asFile.get().absolutePath + "/compose_metrics"
                freeCompilerArgs.addAll(
                    listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$destinationDir",
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$destinationDir"
                    )
                )
            }
        }
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        val version = candidate.version.toLowerCaseAsciiOnly()

        listOf("-dev", "-alpha", "-beta", "-rc")
            .any { it in version }
    }
}
