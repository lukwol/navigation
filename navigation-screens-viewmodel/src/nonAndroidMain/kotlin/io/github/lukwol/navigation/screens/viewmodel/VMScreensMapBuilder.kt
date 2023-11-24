package io.github.lukwol.navigation.screens.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.github.lukwol.navigation.screens.LocalScreensController
import io.github.lukwol.navigation.screens.ScreensMapBuilder
import kotlinx.coroutines.cancel

actual class VMScreensMapBuilder internal constructor(
    @PublishedApi
    internal val viewModelStore: ViewModelStore,
    @PublishedApi
    internal actual val screensMapBuilder: ScreensMapBuilder = ScreensMapBuilder(),
) {
    @Suppress("UNCHECKED_CAST")
    actual inline fun <reified Args, VM : ViewModel> screen(
        route: String,
        noinline viewModelWithArgs: @Composable (args: Args?) -> VM,
        noinline content: @Composable (viewModel: VM) -> Unit,
    ) = screensMapBuilder.screen(route) { args: Args? ->
        val screensController = LocalScreensController.current

        val viewModel = viewModelStore.getOrPut(route) { viewModelWithArgs(args) } as VM
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

    actual fun <VM : ViewModel> screen(
        route: String,
        viewModelFactory: @Composable () -> VM,
        content: @Composable (viewModel: VM) -> Unit,
    ): Unit =
        screen(
            route = route,
            viewModelWithArgs = { _: Any? ->
                viewModelFactory()
            },
            content = content,
        )
}
