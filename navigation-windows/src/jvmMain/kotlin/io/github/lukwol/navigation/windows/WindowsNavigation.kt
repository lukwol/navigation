package io.github.lukwol.navigation.windows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

/**
 * Declare windows navigation by defining all [windows][WindowsMapBuilder.window] for window routes.
 *
 * Provides [LocalWindowController] that can be used for navigation inside each window.
 *
 * @param startRoute first window route for which window will be displayed
 * @param builder the builder used to construct the windows navigation map
 */

@Composable
fun WindowsNavigation(
    startRoute: String,
    builder: WindowsMapBuilder.() -> Unit,
) {
    val mapBuilder = WindowsMapBuilder()
    builder(mapBuilder)

    val windowsMap = remember { mapBuilder.build() }
    val windowsController = remember { WindowsController(startRoute) }

    val routes = windowsController.routesList

    CompositionLocalProvider(
        LocalWindowController provides windowsController,
    ) {
        windowsMap.forEach { (route, content) ->
            @Composable
            fun window() = routes
                .find { it.route == route }
                ?.let { content(it.args) }
            window()
        }
    }
}
