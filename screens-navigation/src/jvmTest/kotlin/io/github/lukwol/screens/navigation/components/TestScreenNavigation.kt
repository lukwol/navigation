package io.github.lukwol.screens.navigation.components

import androidx.compose.runtime.Composable
import io.github.lukwol.screens.navigation.ScreensNavigation
import io.github.lukwol.screens.navigation.data.TestRoutes
import io.github.lukwol.screens.navigation.data.ThirdScreenArgs

@Suppress("TestFunctionName")
@Composable
fun TestScreenNavigation() {
    ScreensNavigation(
        startRoute = TestRoutes.FirstScreen
    ) {
        screen(TestRoutes.FirstScreen) {
            FirstScreen()
        }

        screen(TestRoutes.SecondScreen) { args ->
            SecondScreen(args as String)
        }

        screen(TestRoutes.ThirdScreen) { args ->
            ThirdScreen(args as ThirdScreenArgs)
        }
    }
}
