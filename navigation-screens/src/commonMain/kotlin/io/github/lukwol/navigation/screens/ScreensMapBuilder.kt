package io.github.lukwol.navigation.screens

import androidx.compose.runtime.Composable

/**
 * Builds screens navigation map.
 *
 * Has separate implementations for Android and non Android targets.
 */
expect open class ScreensMapBuilder {

    /**
     * Declare screen [content] for [route] and add it to underlying screens map.
     * The screens map will be used to navigate between the screens.
     *
     * @param route used to identify the screen when navigating, in order to present it's content
     * @param content [Composable] lambda with content of the screen that takes
     * screen arguments as a parameter
     *
     * @throws IllegalArgumentException when attempting to add new screen for already registered [route]
     */
    inline fun <reified Args> screen(
        route: String,
        noinline content: @Composable (args: Args?) -> Unit,
    )

    /**
     * Alternative implementation of [screen] that does not pass any arguments.
     *
     * @see screen
     */
    fun screen(
        route: String,
        content: @Composable () -> Unit,
    )
}
