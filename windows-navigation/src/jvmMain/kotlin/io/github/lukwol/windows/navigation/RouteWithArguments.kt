package io.github.lukwol.windows.navigation

import androidx.compose.runtime.Immutable

/**
 * A pair of [WindowRoute] and [WindowArguments].
 */
@Immutable
internal data class RouteWithArguments(
    val route: WindowRoute,
    val arguments: WindowArguments? = null
)
