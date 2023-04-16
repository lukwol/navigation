package io.github.lukwol.examples.screens

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import io.github.lukwol.screens.navigation.LocalScreensController
import io.github.lukwol.screens.navigation.ScreenArguments
import io.github.lukwol.screens.navigation.ScreenRoute
import io.github.lukwol.viewmodel.CoroutineScopeAware
import io.github.lukwol.viewmodel.screens.navigation.VMScreensMapBuilder

fun <VM : CoroutineScopeAware> VMScreensMapBuilder.androidScreen(
    route: ScreenRoute,
    viewModelFactory: @Composable (args: ScreenArguments?) -> VM,
    content: @Composable (viewModel: VM) -> Unit,
) = screen(route, viewModelFactory) {
    val screensController = LocalScreensController.current

    content(it)

    BackHandler {
        screensController.pop()
    }
}
