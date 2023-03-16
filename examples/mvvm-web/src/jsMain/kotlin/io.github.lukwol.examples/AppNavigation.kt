package io.github.lukwol.examples

import androidx.compose.runtime.Composable
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
        screen(
            route = AppRoutes.FirstScreenRoute,
            viewModelFactory = { FirstScreenViewModel() },
        ) { viewModel ->
            FirstScreen(viewModel)
        }
        screen(
            route = AppRoutes.SecondScreenRoute,
            viewModelFactory = { args -> SecondScreenViewModel(args as String) },
        ) { viewModel ->
            SecondScreen(viewModel)
        }
    }
}
