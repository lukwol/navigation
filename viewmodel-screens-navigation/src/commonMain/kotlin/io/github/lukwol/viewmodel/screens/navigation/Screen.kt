package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import io.github.lukwol.screens.navigation.LocalScreensController
import io.github.lukwol.screens.navigation.ScreenArguments
import io.github.lukwol.screens.navigation.ScreenRoute
import io.github.lukwol.screens.navigation.ScreensMapBuilder
import io.github.lukwol.viewmodel.ViewModel
import io.github.lukwol.viewmodel.ViewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

/**
 * Declare screen [content] for [route] and provide it with [ViewModel].
 *
 * @param VM generic parameter of [ViewModel] that implements [ViewModelScope]
 * @param route [ScreenRoute] used to navigate to the [screen]
 * @param viewModelFactory lambda that takes screen [ScreenArguments] and creates [ViewModel]
 * @param content [Composable] content of the screen
 *
 * @see ScreensMapBuilder.screen
 */
@Suppress("UNCHECKED_CAST")
fun <VM : ViewModelScope> ScreensMapBuilder.screen(
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
                viewModelStore
                    .keys
                    .filterNot { it in screensController.routes }
                    .forEach { disposedRoute ->
                        viewModelStore[disposedRoute]?.viewModelScope?.cancel()
                        viewModelStore.remove(disposedRoute)
                    }
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                viewModelStore.values
                    .map(ViewModelScope::viewModelScope)
                    .forEach(CoroutineScope::cancel)
            }
        }
    }
}
