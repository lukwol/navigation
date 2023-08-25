package io.github.lukwol.navigation.screens.viewmodel.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.viewmodel.ScreensNavigation
import io.github.lukwol.navigation.screens.viewmodel.data.TestRoutes

@OptIn(ExperimentalAnimationApi::class)
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
