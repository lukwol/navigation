package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Composable

actual open class ScreensMapBuilder {

    /**
     * Map of all registered screen routes and their corresponding contents
     * represented as a [Composable] lambda.
     */
    val screensMap = mutableMapOf<String, @Composable (Any?) -> Unit>()

    @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
    actual inline fun <Args> screen(
        route: String,
        noinline content: @Composable (args: Args?) -> Unit,
    ) {
        if (screensMap.containsKey(route)) {
            throw IllegalArgumentException("$route is already registered")
        } else {
            screensMap[route] = {
                content(it as Args?)
            }
        }
    }

    actual fun screen(
        route: String,
        content: @Composable () -> Unit,
    ) = screen<Unit>(
        route = route,
        content = { content() },
    )

    /**
     * Build immutable map of with [ScreenRoute] as keys and contents as values.
     */
    fun build() = screensMap.toMap()
}
