package io.github.lukwol.cmnav.windows

import androidx.compose.runtime.Immutable

/**
 * A pair of window route and window arguments.
 */
@Immutable
internal data class RouteWithArgs(
    val route: String,
    val args: Any? = null,
)
