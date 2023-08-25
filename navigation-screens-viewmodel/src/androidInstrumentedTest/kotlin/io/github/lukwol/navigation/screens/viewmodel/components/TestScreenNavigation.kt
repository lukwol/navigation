package io.github.lukwol.navigation.screens.viewmodel.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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
            viewModelFactory = { viewModel<FirstScreenViewModel>() },
        ) { viewModel ->
            FirstScreen(viewModel)
        }

        screen(
            route = TestRoutes.SecondScreen,
            viewModelWithArgs = { args: String? ->
                viewModel<SecondScreenViewModel>()
                    .apply { text = args }
            },
        ) { viewModel ->
            SecondScreen(viewModel)
        }
    }
}
