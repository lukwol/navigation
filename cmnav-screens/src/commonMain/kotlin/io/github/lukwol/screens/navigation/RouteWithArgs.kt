package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Immutable

/**
 * A pair of screen route and screen [args].
 */
@Immutable
@PublishedApi
internal data class RouteWithArgs(
    val route: String,
    val args: Any? = null,
)
