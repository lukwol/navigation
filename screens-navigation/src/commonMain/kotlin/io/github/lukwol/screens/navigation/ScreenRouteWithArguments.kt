package io.github.lukwol.screens.navigation

/**
 * A pair of [ScreenRoute] and [ScreenArguments].
 */
internal data class ScreenRouteWithArguments(
    val route: ScreenRoute,
    val arguments: ScreenArguments? = null
)
