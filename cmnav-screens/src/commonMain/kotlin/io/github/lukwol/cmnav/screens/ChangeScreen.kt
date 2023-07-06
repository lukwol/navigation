package io.github.lukwol.cmnav.screens

import androidx.compose.runtime.Composable

/**
 * Changes current screen with cross-fade animation.
 *
 * Has separate implementations for JS and non JS targets.
 *
 * Since Compose html doesn't include animations, screens are changed without animation.
 *
 * Other targets have common implementation that animates screen change with cross-fade effect.
 *
 * @param route Screen route to which screen should be changed
 * @param content [Composable] lambda with content of the screen for given [route]
 */
@Composable
internal expect fun ChangeScreen(
    route: RouteWithArgs,
    content: @Composable (RouteWithArgs) -> Unit,
)
