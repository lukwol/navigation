package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Immutable

/**
 * A pair of [ScreenRoute] and [ScreenArguments].
 */
@Immutable
internal data class RouteWithArguments(
    val route: ScreenRoute,
    val arguments: ScreenArguments? = null,
)
