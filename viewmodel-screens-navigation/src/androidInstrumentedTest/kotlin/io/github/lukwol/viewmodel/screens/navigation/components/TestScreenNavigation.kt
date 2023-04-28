package io.github.lukwol.viewmodel.screens.navigation.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.lukwol.viewmodel.screens.navigation.ScreensNavigation
import io.github.lukwol.viewmodel.screens.navigation.data.TestRoutes

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
