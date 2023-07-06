package io.github.lukwol.examples

import androidx.compose.runtime.Composable
import io.github.lukwol.cmnav.screens.ScreensNavigation
import io.github.lukwol.examples.screens.FirstScreen
import io.github.lukwol.examples.screens.SecondScreen
import io.github.lukwol.examples.screens.ThirdScreen

@Composable
fun AppNavigation() {
    io.github.lukwol.cmnav.screens.ScreensNavigation(
        startRoute = AppRoutes.FirstScreenRoute,
    ) {
        screen(AppRoutes.FirstScreenRoute) {
            FirstScreen()
        }

        screen(AppRoutes.SecondScreenRoute) { args: String? ->
            SecondScreen(args)
        }

        screen(AppRoutes.ThirdScreenRoute) { args: List<String>? ->
            ThirdScreen(args)
        }
    }
}
