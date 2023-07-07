import org.gradle.api.JavaVersion.VERSION_11

object BuildConstants {
    const val Group = "io.github.lukwol"
    const val Version = "0.1.0"
    const val JvmTarget = "11"

    object Modules {
        const val CmnavScreens = "cmnav-screens"
        const val CmnavScreensVm = "cmnav-screens-vm"
        const val CmnavWindows = "cmnav-windows"

        val String.Namespace get() = "${Group}.${replace("-", ".")}"
    }

    object Android {
        const val CompileSdk = 33
        const val MinSdk = 24
        val JavaVersion = VERSION_11
        const val TestInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object IOS {
        const val ExamplesDeploymentTarget = "14.1"
    }
}