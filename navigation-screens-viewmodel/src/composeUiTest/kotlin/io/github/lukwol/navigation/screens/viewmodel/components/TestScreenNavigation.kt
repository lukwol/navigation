package io.github.lukwol.navigation.screens.viewmodel.components

import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.viewmodel.ScreensNavigation
import io.github.lukwol.navigation.screens.viewmodel.data.TestRoutes

@Suppress("TestFunctionName")
@Composable
fun TestScreenNavigation() {
    ScreensNavigation(
        startRoute = TestRoutes.FirstScreen,
    ) {
        screen(
            route = TestRoutes.FirstScreen,
            viewModelFactory = { firstViewModel() },
        ) { viewModel ->
            FirstScreen(viewModel)
        }

        screen(
            route = TestRoutes.SecondScreen,
            viewModelWithArgs = { args: String? ->
                secondScreenViewModel().apply { text = args }
            },
        ) { viewModel ->
            SecondScreen(viewModel)
        }
    }
}
