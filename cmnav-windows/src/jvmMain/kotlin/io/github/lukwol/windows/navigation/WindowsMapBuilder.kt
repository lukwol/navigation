package io.github.lukwol.windows.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window

/**
 * Builds windows navigation map.
 *
 * @see WindowsNavigation
 */
class WindowsMapBuilder {

    /**
     * Map of all registered window routes and their corresponding contents
     * represented as a [Composable] lambda.
     */
    private val windowsMap = mutableMapOf<String, @Composable (args: Any?) -> Unit>()

    /**
     * Declare window [content] for [route] and add it to underlying [windowsMap].
     * The [windowsMap] will be used to navigate between the windows.
     *
     * @param route used to identify the window when navigating, in order to present it's content
     * @param windowFactory lambda that takes [content] argument
     * and allows to wrap it in custom [Window] implementation.
     * @param content view with [Composable] content of the window
     *
     * @throws IllegalArgumentException when attempting to add new window for already registered [route]
     */
    @Suppress("UNCHECKED_CAST")
    fun <Args> window(
        route: String,
        windowFactory: @Composable (content: @Composable FrameWindowScope.() -> Unit) -> Unit,
        content: @Composable FrameWindowScope.(args: Args?) -> Unit,
    ) {
        if (windowsMap.containsKey(route)) {
            throw IllegalArgumentException("$route is already registered")
        } else {
            windowsMap[route] = {
                windowFactory { content(it as Args?) }
            }
        }
    }

    /**
     * Alternative implementation of [window] that does not pass any arguments.
     *
     * @see window
     */
    fun window(
        route: String,
        windowFactory: @Composable (content: @Composable FrameWindowScope.() -> Unit) -> Unit,
        content: @Composable FrameWindowScope.() -> Unit,
    ) = window(
        route = route,
        windowFactory = windowFactory,
        content = { _: Any? ->
            content()
        },
    )

    /**
     * Alternative implementation of [window] that provides [Window] with basic setup.
     * Window title is set to [title] parameter and `onCloseRequest` is set to close this window.
     *
     * @see window
     */
    fun <Args> window(
        route: String,
        title: String = route,
        content: @Composable FrameWindowScope.(args: Args?) -> Unit,
    ) {
        window(
            route = route,
            windowFactory = {
                val windowsController = LocalWindowController.current

                Window(
                    title = title,
                    onCloseRequest = {
                        windowsController.close(route)
                    },
                    content = it,
                )
            },
            content = content,
        )
    }

    /**
     * Alternative implementation of [window] that does not pass any arguments
     * and that provides [Window] with basic setup.
     * Window title is set to [title] parameter and `onCloseRequest` is set to close this window.
     *
     * @see window
     */
    fun window(
        route: String,
        title: String = route,
        content: @Composable FrameWindowScope.() -> Unit,
    ) = window(
        route = route,
        title = title,
        content = { _: Any? ->
            content()
        },
    )

    /**
     * Build immutable [windowsMap].
     */
    fun build() = windowsMap.toMap()
}
