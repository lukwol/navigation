package io.github.lukwol.examples

import io.github.lukwol.examples.screens.FirstScreen
import io.github.lukwol.examples.screens.SecondScreen
import io.github.lukwol.examples.screens.ThirdScreen
import io.github.lukwol.examples.screens.createFirstScreenViewModel
import io.github.lukwol.examples.screens.createSecondScreenViewModel
import io.github.lukwol.examples.screens.createThirdScreenViewModel
import io.github.lukwol.navigation.screens.viewmodel.VMScreensMapBuilder

fun VMScreensMapBuilder.appNavigationContent() {
    screen(
        route = AppRoutes.FirstScreenRoute,
        viewModelFactory = { createFirstScreenViewModel() },
    ) { viewModel ->
        FirstScreen(viewModel)
    }

    screen(
        route = AppRoutes.SecondScreenRoute,
        viewModelWithArgs = { args: String? ->
            createSecondScreenViewModel().apply {
                firstText = args.orEmpty().ifEmpty { "No text passed" }
            }
        },
    ) { viewModel ->
        SecondScreen(viewModel)
    }

    screen(
        route = AppRoutes.ThirdScreenRoute,
        viewModelWithArgs = { args: List<String>? ->
            createThirdScreenViewModel().apply {
                texts = args.orEmpty()
            }
        },
    ) { viewModel ->
        ThirdScreen(viewModel)
    }
}
