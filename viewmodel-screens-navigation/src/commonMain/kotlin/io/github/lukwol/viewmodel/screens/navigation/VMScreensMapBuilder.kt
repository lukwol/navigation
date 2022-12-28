package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import io.github.lukwol.screens.navigation.*
import io.github.lukwol.viewmodel.ViewModel
import io.github.lukwol.viewmodel.ViewModelScope
import kotlinx.coroutines.cancel

/**
 * Builds screens navigation map same as [BasicScreensMapBuilder],
 * additionally allows passing [ViewModel] via lambda.
 */
class VMScreensMapBuilder internal constructor(
    private val viewModelStore: ViewModelStore,
    private val basicScreensMapBuilder: BasicScreensMapBuilder = BasicScreensMapBuilder()
) : ScreensMapBuilder by basicScreensMapBuilder {

    /**
     * Declare screen [content] for [route] and provide it with [ViewModel].
     *
     * @param VM generic parameter of [ViewModel] that implements [ViewModelScope]
     * @param route [ScreenRoute] used to navigate to the [screen]
     * @param viewModelFactory lambda that takes screen [ScreenArguments] and creates [ViewModel]
     * @param content [Composable] content of the screen
     *
     * @see BasicScreensMapBuilder.screen
     */
    @Suppress("UNCHECKED_CAST")
    fun <VM : ViewModelScope> screen(
        route: ScreenRoute,
        viewModelFactory: (args: ScreenArguments?) -> VM,
        content: @Composable (viewModel: VM) -> Unit
    ) = screen(route) { arguments ->
        val screensController = LocalScreensController.current

        val viewModel = remember(route) {
            viewModelStore.getOrPut(route) { viewModelFactory(arguments) } as VM
        }
        content(viewModel)

        LaunchedEffect(route) {
            viewModelStore
                .keys
                .filterNot { it in screensController.routes }
                .forEach { disposedRoute ->
                    viewModelStore[disposedRoute]?.viewModelScope?.cancel()
                    viewModelStore.remove(disposedRoute)
                }
        }
    }
}
