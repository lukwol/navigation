package io.github.lukwol.screens.navigation

/**
 * A pair of [ScreenRoute] and [Arguments].
 */
internal data class RouteWithArguments(
    val route: ScreenRoute,
    val arguments: Arguments? = null
)
