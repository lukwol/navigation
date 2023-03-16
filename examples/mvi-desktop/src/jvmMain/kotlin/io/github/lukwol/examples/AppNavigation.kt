package io.github.lukwol.examples

import androidx.compose.runtime.Composable
import io.github.lukwol.examples.screens.first.FirstScreen
import io.github.lukwol.examples.screens.first.FirstScreenViewModel
import io.github.lukwol.examples.screens.second.SecondScreen
import io.github.lukwol.examples.screens.second.SecondScreenViewModel
import io.github.lukwol.examples.screens.third.ThirdScreen
import io.github.lukwol.examples.screens.third.ThirdScreenViewModel
import io.github.lukwol.viewmodel.screens.navigation.ScreensNavigation

@Composable
fun AppNavigation() {
    ScreensNavigation(
        startRoute = AppRoutes.firstScreen,
        animated = true,
    ) {
        screen(
            route = AppRoutes.firstScreen,
            viewModelFactory = { FirstScreenViewModel() },
        ) {
            FirstScreen(
                state = it.state,
                commands = it::onCommand,
            )
        }

        screen(
            route = AppRoutes.secondScreen,
            viewModelFactory = { SecondScreenViewModel() },

        ) {
            SecondScreen(
                state = it.state,
                commands = it::onCommand,
            )
        }

        screen(
            route = AppRoutes.thirdScreen,
            viewModelFactory = { ThirdScreenViewModel() },
        ) {
            ThirdScreen(
                state = it.state,
                commands = it::onCommand,
            )
        }
    }
}
