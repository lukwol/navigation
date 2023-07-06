package io.github.lukwol.cmnav.screens.vm.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.lukwol.cmnav.screens.vm.ScreensNavigation
import io.github.lukwol.viewmodel.cmnav.screens.vm.TestRoutes

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
