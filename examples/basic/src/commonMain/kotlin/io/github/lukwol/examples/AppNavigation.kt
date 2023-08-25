package io.github.lukwol.examples

import androidx.compose.runtime.Composable
import io.github.lukwol.examples.screens.FirstScreen
import io.github.lukwol.examples.screens.SecondScreen
import io.github.lukwol.examples.screens.ThirdScreen
import io.github.lukwol.navigation.screens.ScreensNavigation

@Composable
fun AppNavigation() {
    io.github.lukwol.navigation.screens.ScreensNavigation(
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
