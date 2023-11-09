package io.github.lukwol.navigation.screens.android.components

import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensNavigation
import io.github.lukwol.navigation.screens.android.data.TestRoutes
import io.github.lukwol.navigation.screens.android.data.ThirdScreenArgs

@Suppress("TestFunctionName")
@Composable
fun TestScreenNavigation() {
    ScreensNavigation(
        startRoute = TestRoutes.FirstScreen,
    ) {
        screen(TestRoutes.FirstScreen) {
            FirstScreen()
        }

        screen(TestRoutes.SecondScreen) { args: String? ->
            SecondScreen(args!!)
        }

        screen(TestRoutes.ThirdScreen) { args: ThirdScreenArgs? ->
            ThirdScreen(args!!)
        }
    }
}
