package io.github.lukwol.windows.navigation

/**
 * A pair of [WindowRoute] and [WindowArguments].
 */
internal data class WindowRouteWithArguments(
    val route: WindowRoute,
    val arguments: WindowArguments? = null
)
