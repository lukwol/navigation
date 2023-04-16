package io.github.lukwol.examples

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.lukwol.examples.screens.androidScreen
import io.github.lukwol.examples.screens.first.FirstScreen
import io.github.lukwol.examples.screens.first.FirstScreenViewModel
import io.github.lukwol.examples.screens.second.SecondScreen
import io.github.lukwol.examples.screens.second.SecondScreenViewModel
import io.github.lukwol.viewmodel.screens.navigation.ScreensNavigation

@Composable
fun AppNavigation() {
    ScreensNavigation(
        startRoute = AppRoutes.FirstScreenRoute,
    ) {
        androidScreen(
            route = AppRoutes.FirstScreenRoute,
            viewModelFactory = { hiltViewModel<FirstScreenViewModel>() },
        ) { viewModel ->
            FirstScreen(viewModel)
        }
        androidScreen(
            route = AppRoutes.SecondScreenRoute,
            viewModelFactory = {
                hiltViewModel<SecondScreenViewModel>().apply {
                    text = (it as String).ifEmpty { "No text passed" }
                }
            },
        ) { viewModel ->
            SecondScreen(viewModel)
        }
    }
}
