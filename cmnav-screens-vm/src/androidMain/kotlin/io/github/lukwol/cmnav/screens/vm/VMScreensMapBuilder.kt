package io.github.lukwol.cmnav.screens.vm

import androidx.compose.runtime.Composable
import io.github.lukwol.cmnav.screens.ScreensMapBuilder

actual class VMScreensMapBuilder(
    actual val screensMapBuilder: ScreensMapBuilder,
) {

    actual inline fun <reified Args, VM : ViewModel> screen(
        route: String,
        noinline viewModelWithArgs: @Composable (args: Args?) -> VM,
        noinline content: @Composable (viewModel: VM) -> Unit,
    ) = screensMapBuilder.screen(route) { args: Args? ->
        val viewModel = viewModelWithArgs(args)
        content(viewModel)
    }

    actual fun <VM : ViewModel> screen(
        route: String,
        viewModelFactory: @Composable () -> VM,
        content: @Composable (viewModel: VM) -> Unit,
    ): Unit = screen(
        route = route,
        viewModelWithArgs = { _: Any? ->
            viewModelFactory()
        },
        content = content,
    )
}
