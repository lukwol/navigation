package io.github.lukwol.viewmodel.screens.navigation.components

import androidx.compose.runtime.Composable
import io.github.lukwol.screens.navigation.ScreensNavigation
import io.github.lukwol.viewmodel.screens.navigation.data.TestRoutes
import io.github.lukwol.viewmodel.screens.navigation.screen

@Suppress("TestFunctionName")
@Composable
fun TestScreenNavigation() {
    ScreensNavigation(
        startRoute = TestRoutes.FirstScreen
    ) {
        screen(
            route = TestRoutes.FirstScreen,
            viewModelFactory = { FirstScreenViewModel() }
        ) { viewModel ->
            FirstScreen(viewModel)
        }

        screen(
            route = TestRoutes.SecondScreen,
            viewModelFactory = { SecondScreenViewModel(it as String) }
        ) { viewModel ->
            SecondScreen(viewModel)
        }
    }
}
