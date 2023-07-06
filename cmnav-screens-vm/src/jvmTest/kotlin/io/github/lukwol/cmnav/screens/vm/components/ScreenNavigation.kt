package io.github.lukwol.cmnav.screens.vm.components

import androidx.compose.runtime.Composable
import io.github.lukwol.cmnav.screens.vm.ScreensNavigation
import io.github.lukwol.cmnav.screens.vm.data.TestRoutes

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
                SecondScreenViewModel(args)
            },
        ) { viewModel ->
            SecondScreen(viewModel)
        }
    }
}
