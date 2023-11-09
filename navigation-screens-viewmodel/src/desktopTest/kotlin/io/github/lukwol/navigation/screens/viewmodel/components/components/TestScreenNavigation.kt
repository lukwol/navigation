package io.github.lukwol.navigation.screens.viewmodel.components.components

import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.viewmodel.ScreensNavigation
import io.github.lukwol.navigation.screens.viewmodel.components.data.TestRoutes

@Suppress("TestFunctionName")
@Composable
fun TestScreenNavigation() {
    ScreensNavigation(
        startRoute = TestRoutes.FirstScreen,
    ) {
        screen(
            route = TestRoutes.FirstScreen,
            viewModelFactory = { FirstScreenViewModel() },
        ) { viewModel ->
            FirstScreen(viewModel)
        }

        screen(
            route = TestRoutes.SecondScreen,
            viewModelWithArgs = { args: String? ->
                SecondScreenViewModel().apply { text = args }
            },
        ) { viewModel ->
            SecondScreen(viewModel)
        }
    }
}
