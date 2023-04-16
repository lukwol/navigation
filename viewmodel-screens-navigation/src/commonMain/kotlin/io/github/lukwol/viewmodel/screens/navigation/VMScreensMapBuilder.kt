package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.github.lukwol.screens.navigation.BasicScreensMapBuilder
import io.github.lukwol.screens.navigation.LocalScreensController
import io.github.lukwol.screens.navigation.ScreenArguments
import io.github.lukwol.screens.navigation.ScreenRoute
import io.github.lukwol.screens.navigation.ScreensMapBuilder
import io.github.lukwol.viewmodel.CoroutineScopeAware
import io.github.lukwol.viewmodel.ViewModel
import kotlinx.coroutines.cancel

/**
 * Builds screens navigation map same as [BasicScreensMapBuilder],
 * additionally allows passing [ViewModel] via lambda.
 */
class VMScreensMapBuilder internal constructor(
    private val viewModelStore: ViewModelStore,
    private val basicScreensMapBuilder: BasicScreensMapBuilder = BasicScreensMapBuilder(),
) : ScreensMapBuilder by basicScreensMapBuilder {

    /**
     * Declare screen [content] for [route] and provide it with [ViewModel].
     *
     * @param VM generic parameter of [ViewModel] that implements [CoroutineScopeAware]
     * @param route [ScreenRoute] used to navigate to the [screen]
     * @param viewModelFactory lambda that takes screen [ScreenArguments] and creates [ViewModel]
     * @param content [Composable] content of the screen
     *
     * @see BasicScreensMapBuilder.screen
     */
    @Suppress("UNCHECKED_CAST")
    fun <VM : CoroutineScopeAware> screen(
        route: ScreenRoute,
        viewModelFactory: @Composable (args: ScreenArguments?) -> VM,
        content: @Composable (viewModel: VM) -> Unit,
    ) = screen(route) { arguments ->
        val screensController = LocalScreensController.current

        val viewModel = viewModelStore.getOrPut(route) { viewModelFactory(arguments) } as VM
        content(viewModel)

        LaunchedEffect(route) {
            viewModelStore
                .keys
                .filterNot { it in screensController.routes }
                .forEach { disposedRoute ->
                    viewModelStore[disposedRoute]?.coroutineScope?.cancel()
                    viewModelStore.remove(disposedRoute)
                }
        }
    }
}
