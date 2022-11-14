package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import io.github.lukwol.screens.navigation.LocalScreensController
import io.github.lukwol.screens.navigation.ScreenArguments
import io.github.lukwol.screens.navigation.ScreenRoute
import io.github.lukwol.screens.navigation.ScreensMapBuilder
import io.github.lukwol.viewmodel.ViewModel
import kotlinx.coroutines.cancel

/**
 * Declare screen [content] for [route] and provide it with [ViewModel].
 *
 * @param VM generic parameter of [ViewModel]
 * @param route [ScreenRoute] used to navigate to the [screen]
 * @param viewModelFactory lambda that takes screen [ScreenArguments] and creates [ViewModel]
 * @param content [Composable] content of the screen
 *
 * @see ScreensMapBuilder.screen
 */
@Suppress("UNCHECKED_CAST")
fun <VM : ViewModel> ScreensMapBuilder.screen(
    route: ScreenRoute,
    viewModelFactory: (args: ScreenArguments?) -> VM,
    content: @Composable (viewModel: VM) -> Unit
) {
    screen(route) { arguments ->
        val screensController = LocalScreensController.current

        val viewModelStore = remember { ViewModelStore() }
        val viewModel = remember(route) {
            viewModelStore.getOrPut(route) { viewModelFactory(arguments) } as VM
        }

        content(viewModel)

        DisposableEffect(route) {
            onDispose {
                if (route !in screensController.routes) {
                    viewModel.viewModelScope.cancel()
                    viewModelStore.remove(route)
                }
            }
        }
    }
}
