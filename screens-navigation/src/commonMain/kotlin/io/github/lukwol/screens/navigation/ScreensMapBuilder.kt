package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Composable

/**
 * Builds screens navigation map.
 */
class ScreensMapBuilder {

    /**
     * Map of all registered [routes][ScreenRoute] to their [content][Composable].
     */
    private val screensMap = mutableMapOf<ScreenRoute, @Composable (ScreenArguments?) -> Unit>()

    /**
     * Declare screen [content] for [route] and add it to [screensMap].
     *
     * @param route [ScreenRoute] used to navigate to the [screen]
     * @param content [Composable] content of the screen
     *
     * @throws IllegalArgumentException when adding screen for already registered [route]
     */
    fun screen(
        route: ScreenRoute,
        content: @Composable (args: ScreenArguments?) -> Unit
    ) {
        if (screensMap.containsKey(route)) {
            throw IllegalArgumentException("$route is already registered")
        } else {
            screensMap[route] = content
        }
    }

    /**
     * Build immutable [screensMap].
     */
    fun build() = screensMap.toMap()
}
