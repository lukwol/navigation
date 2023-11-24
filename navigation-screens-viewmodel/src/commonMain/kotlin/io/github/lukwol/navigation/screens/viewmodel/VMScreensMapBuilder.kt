package io.github.lukwol.navigation.screens.viewmodel

import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensMapBuilder

/**
 * Builds screens navigation map same as [ScreensMapBuilder],
 * additionally allows passing [ViewModel] via lambda.
 *
 * Has separate implementations for Android and non Android targets.
 *
 * Both implementation uses [ScreensMapBuilder] underneath and adds support for lifecycle aware [ViewModel].
 *
 * Non Android implementation manually manages [ViewModel] persistence
 * using `ViewModelStore` and cancels [ViewModel.coroutineScope] whenever screen or window is disposed.
 *
 * Android implementation relies on built in solutions from Google **Lifecycle* framework.
 */
expect class VMScreensMapBuilder {
    @PublishedApi
    internal val screensMapBuilder: ScreensMapBuilder

    /**
     * Declare screen [content] for [route] and add it to underlying screens map.
     * The screens map will be used to navigate between the screens.
     *
     * @param VM generic type of [ViewModel]
     * @param route used to identify the screen when navigating, in order to present it's content
     * @param viewModelWithArgs lambda that takes screen arguments as a parameter
     * and creates [ViewModel] that is later passed to screen [content] lambda
     * @param content [Composable] lambda with content of the screen that takes
     * [view model][VM] as a parameter
     *
     * @throws IllegalArgumentException when attempting to add new screen for already registered [route]
     */
    inline fun <reified Args, VM : ViewModel> screen(
        route: String,
        noinline viewModelWithArgs: @Composable (args: Args?) -> VM,
        noinline content: @Composable (viewModel: VM) -> Unit,
    )

    /**
     * Alternative implementation of [screen] that does not pass any arguments.
     *
     * @see screen
     */
    fun <VM : ViewModel> screen(
        route: String,
        viewModelFactory: @Composable () -> VM,
        content: @Composable (viewModel: VM) -> Unit,
    )
}

/**
 * Implementation forwarded from ScreensNavigation
 * which does not involve [ViewModel] initialization.
 *
 * @see ScreensMapBuilder.screen
 */
inline fun <reified Args> VMScreensMapBuilder.screen(
    route: String,
    noinline content: @Composable (args: Args?) -> Unit,
) = screensMapBuilder.screen(route, content)

/**
 * Implementation forwarded from ScreensNavigation that does not pass any arguments
 * and does not involve [ViewModel] initialization.
 *
 * @see ScreensMapBuilder.screen
 */
fun VMScreensMapBuilder.screen(
    route: String,
    content: @Composable () -> Unit,
) = screensMapBuilder.screen(route, content)
