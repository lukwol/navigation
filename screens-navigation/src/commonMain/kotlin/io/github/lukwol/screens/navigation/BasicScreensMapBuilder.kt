package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Composable

interface ScreensMapBuilder {
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
    )

    /**
     * Build immutable map of with [ScreenRoute] as keys and contents as values.
     */
    fun build(): Map<ScreenRoute, @Composable (ScreenArguments?) -> Unit>
}

/**
 * Builds screens navigation map.
 */
class BasicScreensMapBuilder : ScreensMapBuilder {

    /**
     * Map of all registered [routes][ScreenRoute] to their [content][Composable].
     */
    private val screensMap = mutableMapOf<ScreenRoute, @Composable (ScreenArguments?) -> Unit>()

    override fun screen(
        route: ScreenRoute,
        content: @Composable (args: ScreenArguments?) -> Unit
    ) {
        if (screensMap.containsKey(route)) {
            throw IllegalArgumentException("$route is already registered")
        } else {
            screensMap[route] = content
        }
    }

    override fun build() = screensMap.toMap()
}
