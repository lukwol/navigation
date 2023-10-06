import org.gradle.api.JavaVersion.VERSION_17

object BuildConstants {
    const val Group = "io.github.lukwol"
    const val VersionName = "1.2.0"
    val JavaVersion = VERSION_17

    object Modules {
        const val NavigationScreens = "navigation-screens"
        const val NavigationScreensViewModel = "navigation-screens-viewmodel"
        const val NavigationWindows = "navigation-windows"

        val String.Namespace get() = "${Group}.${replace("-", ".")}"
    }

    object Android {
        const val ApplicationId = "$Group.app.android"
        const val MinSdk = 29
        const val TargetSdk = 34
        const val CompileSdk = TargetSdk

        const val TestInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
