package io.github.lukwol.navigation.screens.components

import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensNavigation
import io.github.lukwol.navigation.screens.data.TestRoutes
import io.github.lukwol.navigation.screens.data.ThirdScreenArgs

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
